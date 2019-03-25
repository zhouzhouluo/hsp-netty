package com.cmcc.hsp.client.netty.handler;

import java.net.InetSocketAddress;

import com.cmcc.hsp.client.netty.model.STBMessageProtocol;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zhouzhou
 *
 */
public class STBNettyClientHandler extends SimpleChannelInboundHandler<STBMessageProtocol> {

    Gson gson = new Gson();

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, STBMessageProtocol msg) throws Exception {

        String content =new String(msg.getContent(),"UTF-8");
        System.out.println("收到服务端消息-content: " +content );
        System.out.println("收到服务端消息: " +gson.fromJson(content,STBMessageProtocol.class) );

//        WsConstant.uuid =
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();
        System.out.println("连接服务器[ip:" + clientIp + ":"+ insocket.getPort()+ "]成功");
//        ctx.writeAndFlush("active发送的消息");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接关闭! ");
        super.channelInactive(ctx);
    }
}
