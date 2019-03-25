package com.cmcc.hsp.netty.handler;

import com.cmcc.hsp.netty.core.NettySocketHolder;
import com.cmcc.hsp.netty.invote.STBActionMapUtil;
import com.cmcc.hsp.netty.model.ConstantValue;
import com.cmcc.hsp.netty.model.STBHeader;
import com.cmcc.hsp.netty.model.STBMessage;
import com.cmcc.hsp.netty.model.STBMessageProtocol;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;
import jdk.nashorn.internal.parser.TokenType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @author zhouzhou
 */
@Component
public class STBServerHandler extends ChannelHandlerAdapter {

    private final static Logger LOGGER = LoggerFactory.getLogger(STBServerHandler.class);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();
        int clientPort = insocket.getPort();
        LOGGER.debug("111111111111111111已上线：客户端的地址为:【" + clientIp + ":"+clientPort+"】");
        String clientSessenId = ctx.channel().id().asLongText();
        LOGGER.debug("2222222222222222222添加连接uuid:" + clientSessenId);
        NettySocketHolder.addGatewayChannel(clientSessenId, (SocketChannel) ctx.channel());

        InetSocketAddress insocketIlocal = (InetSocketAddress) ctx.channel().localAddress();
        String content = "我收到连接ppp,服务器未:"+insocketIlocal.getAddress()+":"+insocketIlocal.getPort();

        STBHeader stbHeader =  new STBHeader();
        stbHeader.setClientSessenId(clientSessenId);
        stbHeader.setCommand(ConstantValue.WS_COMMAND_KEY);
        STBMessage<String> stbMessage = new STBMessage<>(stbHeader, content);
        STBMessageProtocol stbMessageProtocol = new STBMessageProtocol(stbMessage);
        ctx.writeAndFlush(stbMessageProtocol);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        String uuid = ctx.channel().id().asLongText();
        LOGGER.debug("异常连接uuid:" + uuid);
        cause.printStackTrace();
        ctx.close();
        NettySocketHolder.removeGatewayChannel(uuid);
    }



    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        String uuid = ctx.channel().id().asLongText();
        LOGGER.debug("断开连接uuid:" + uuid);
        ctx.close();
        NettySocketHolder.removeGatewayChannel(uuid);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Gson gson =  new Gson();
        LOGGER.debug("msg:" + msg);
        STBMessageProtocol stbMessageProtocol = (STBMessageProtocol) msg;
        String content = new String (stbMessageProtocol.getContent(),"UTF-8");
        LOGGER.debug("content:" + content);
        STBMessage<Object> stbMessage= gson.fromJson(content,new TypeToken<STBMessage<Object>>(){}.getType());

        /* 请求分发*/
        STBActionMapUtil.invote(stbMessage.getStbHeader().getCommand(), ctx, stbMessage);
    }


}