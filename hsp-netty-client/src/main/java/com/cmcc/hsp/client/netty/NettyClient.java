package com.cmcc.hsp.client.netty;

import javax.annotation.PostConstruct;

import com.cmcc.hsp.client.netty.filter.NettyClientFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

@Component
public class NettyClient {
    /**
     * 主机
     */
    @Value("${netty.host}")
    private String host;
    /**
     * 端口号
     */
    @Value("${netty.port}")
    private int port;

    private EventLoopGroup eventLoopGroup;
    private Bootstrap bootstrap;
    private ChannelFuture channelFuture;


    /**
     * 此注解会在类创建时被调用
     * @throws InterruptedException
     */
    public void init() throws InterruptedException {
        eventLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new NettyClientFilter());

        channelFuture = bootstrap.connect(host, port).sync();
    }

    public void connect() {
        try {
            // 发送json字符串
            this.channelFuture = this.bootstrap.connect(host, port).sync();
            System.out.println("远程服务器已经连接, 可以进行数据交换..");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ChannelFuture getChannelFuture() {
        // 如果没有连接先链接
        if (this.channelFuture == null) {
            this.connect();
        } // this.cf.channel().isActive() 这里得到的是链接状态
        if (!this.channelFuture.channel().isActive()) {
            this.connect();
        }
        return this.channelFuture;
    }

    //释放资源
    public void close() {
        try {
            channelFuture.channel().closeFuture().sync();
            eventLoopGroup.shutdownGracefully();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}