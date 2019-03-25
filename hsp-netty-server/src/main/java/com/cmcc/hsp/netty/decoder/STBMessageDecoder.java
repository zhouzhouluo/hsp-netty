package com.cmcc.hsp.netty.decoder;

import com.cmcc.hsp.netty.model.ConstantValue;
import com.cmcc.hsp.netty.model.STBMessageProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 *
 */
public class STBMessageDecoder extends ByteToMessageDecoder {

    /**
     * 协议开始的标准head_data,int 类型，占据4个字节
     * 表示数据的长度contentLength,int类型，占据4个字节
     */
    public final int BASE_LENGTH = 4 + 4;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("byteBuf.readableBytes()--------------------:"+byteBuf.readableBytes());
        //可读长度必须大于基本长度
        if (byteBuf.readableBytes() >= BASE_LENGTH) {

            //防止socket字节流攻击
            //防止，客户端传来的数据过大
            //因为，太大的数据，是不合理的
            if (byteBuf.readableBytes() > 2048) {
                byteBuf.skipBytes(byteBuf.readableBytes());
            }

            //记录包头开始的index
            int beginReader;
            while (true) {
                //获取包头开始的index
                beginReader = byteBuf.readerIndex();

                //标记包头开始的index
                byteBuf.markReaderIndex();
                if (byteBuf.readInt() == ConstantValue.HEAD_DATA) {
                    break;
                }

                //未读到包头，略过一个字节
                //每次略过，一个字节，去读取，包头信息的开始标记
                byteBuf.resetReaderIndex();
                byteBuf.readByte();

                //当略过，一个字节之后
                //数据包的长度，又变得不满足
                //此时，应该结束。等待后面的数据到达
                if (byteBuf.readableBytes() < BASE_LENGTH) {
                    return;
                }

            }

            //消息的长度
            int length = byteBuf.readInt();
            //判断请求数据包数据是否到齐
            if (byteBuf.readableBytes() < length) {

                //还原读指针
                byteBuf.readerIndex(beginReader);
                return;
            }

            //读取Data数据
            byte[] data = new byte[length];
            byteBuf.readBytes(data);

            STBMessageProtocol stbMessageProtocol = new STBMessageProtocol(data.length, data);
            list.add(stbMessageProtocol);

        }

    }
}
