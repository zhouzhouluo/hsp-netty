package com.cmcc.hsp.client.netty.filter;

import com.cmcc.hsp.client.netty.decoder.STBMessageDecoder;
import com.cmcc.hsp.client.netty.encoder.STBMessageEncoder;
import com.cmcc.hsp.client.netty.handler.STBNettyClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 *
 * Title: NettyClientFilter
 * Description: Netty客户端 过滤器
 * Version:1.0.0
 * @author Administrator
 * @date 2017-8-31
 */
public class NettyClientFilter extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline ph = ch.pipeline();
        ph.addFirst(new LineBasedFrameDecoder(65535));
        ph.addLast("decoder", new STBMessageDecoder());
        ph.addLast("encoder", new STBMessageEncoder());
        ph.addLast("handler", new STBNettyClientHandler());
    }
}