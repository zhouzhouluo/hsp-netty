package com.cmcc.hsp.netty.handler;

import com.cmcc.hsp.netty.core.NettySocketHolder;
import com.cmcc.hsp.netty.model.*;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class STBMessageHandler<T> {


    /**
     * 给某个用户发送消息
     * @param client_uuid
     * @param t
     * @throws Exception
     */
    public void  sendMessageToUserByUuid(String client_uuid, T t,String action) throws Exception {
        log.info("给某个用户" + client_uuid + "发送消息:TextMessage:" + t);
        STBHeader stbHeader = new STBHeader();
        stbHeader.setClientSessenId("");
        stbHeader.setClientUuId(client_uuid);
        stbHeader.setCommand(action);
        STBMessage<T> stbMessage = new STBMessage<>(stbHeader, t);
        STBMessageProtocol stbMessageProtocol = new STBMessageProtocol(stbMessage);
        sendMessage(client_uuid,stbMessageProtocol);
    }

    /**
     * 给某个用户发送消息
     * @param clientUuid
     * @param stbMessageProtocol
     * @throws Exception
     */
    public void sendMessage(String clientUuid, STBMessageProtocol stbMessageProtocol) throws Exception {
        SocketChannel socketChannel = NettySocketHolder.getGatewayChannel(clientUuid);
        if (socketChannel.isOpen()) {
            socketChannel.writeAndFlush(stbMessageProtocol);
        }
    }



}
