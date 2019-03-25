package com.cmcc.hsp.netty.filter;

import com.cmcc.hsp.netty.decoder.STBMessageDecoder;
import com.cmcc.hsp.netty.encoder.STBMessageEncoder;
import com.cmcc.hsp.netty.handler.STBServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * @author zhouzhou
 */
public class STBServerChannelFilter extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline()
                .addLast("encoder", new STBMessageEncoder())
                .addLast("decoder", new STBMessageDecoder())
                .addFirst(new LineBasedFrameDecoder(65535))
                .addLast(new STBServerHandler());
    }
}