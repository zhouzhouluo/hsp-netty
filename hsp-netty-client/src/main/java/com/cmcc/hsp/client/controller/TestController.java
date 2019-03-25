package com.cmcc.hsp.client.controller;

import com.cmcc.hsp.client.netty.NettyClient;
import com.cmcc.hsp.client.netty.model.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.util.Date;

/**
 * @author zhouzhou
 */
@RestController
public class TestController {

    Gson gson = new Gson();

    @Autowired
    NettyClient nettyClient;

    @RequestMapping("/test/{usename}")
    public STBMessage<LoginUser> sendMsg(@PathVariable("usename") String usename) throws Exception{
        //获取建立的channel
        ChannelFuture cf = nettyClient.getChannelFuture();
        Channel channel = cf.channel();
        System.out.println("testcontroller获得的channel---" + channel.toString());
        STBHeader stbHeader = new STBHeader();
        stbHeader.setClientSessenId(ConstantValue.clientSessenId);
        stbHeader.setClientUuId(ConstantValue.clientUuId);
        stbHeader.setCommand(ConstantValue.WS_COMMAND_LOGIN);
        LoginUser loginUser = new LoginUser();
        loginUser.setPassword("123");
        loginUser.setUserName(usename);
        loginUser.setLonginDate(new Date());
        STBMessage<LoginUser> stbMessage = new STBMessage<>(stbHeader, loginUser);
        STBMessageProtocol stbMessageProtocol = new STBMessageProtocol(stbMessage);
        channel.writeAndFlush(stbMessageProtocol);

        return stbMessage;
    }
}