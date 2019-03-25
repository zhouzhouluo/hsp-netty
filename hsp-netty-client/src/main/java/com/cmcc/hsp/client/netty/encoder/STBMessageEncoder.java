package com.cmcc.hsp.client.netty.encoder;

import com.cmcc.hsp.client.netty.model.STBMessageProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


public class STBMessageEncoder extends MessageToByteEncoder<STBMessageProtocol> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, STBMessageProtocol stbMessageProtocol, ByteBuf byteBuf) throws Exception {


        byteBuf.writeInt(stbMessageProtocol.getHead_data());

        byteBuf.writeInt(stbMessageProtocol.getContentLength());

        byteBuf.writeBytes(stbMessageProtocol.getContent());

        byteBuf.writeChar('\n');
    }

}
