package com.cmcc.hsp.netty.encoder;

import com.cmcc.hsp.netty.model.STBMessageProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


public class STBMessageEncoder extends MessageToByteEncoder<STBMessageProtocol> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, STBMessageProtocol stbMessageProtocol, ByteBuf byteBuf) throws Exception {

        System.out.println("stbMessageProtocol.getHead_data()111111111111:"+stbMessageProtocol.getHead_data());
        byteBuf.writeInt(stbMessageProtocol.getHead_data());

        System.out.println("stbMessageProtocol.getContentLength()222222222222:"+stbMessageProtocol.getContentLength());
        byteBuf.writeInt(stbMessageProtocol.getContentLength());

        System.out.println("stbMessageProtocol.getContent()3333333333333:"+stbMessageProtocol.getContent());
        System.out.println("stbMessageProtocol.getContentToJson():"+stbMessageProtocol.getContentToJson());

        byteBuf.writeBytes(stbMessageProtocol.getContent());

        byteBuf.writeChar('\n');


    }

}
