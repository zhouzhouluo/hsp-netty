package com.cmcc.hsp.netty.controller;

import com.cmcc.hsp.netty.core.NettySocketHolder;
import com.cmcc.hsp.netty.handler.STBMessageHandler;
import com.cmcc.hsp.netty.model.ConstantValue;
import com.cmcc.hsp.netty.model.LoginUser;
import com.cmcc.hsp.netty.model.STBMessage;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private STBMessageHandler<LoginUser> stbMessageHandler;

    private Gson gson = new Gson();

    @RequestMapping("login/{uuid}")
    public Map<String, Object> login(@PathVariable String uuid, String name, String pwd) {
        Map<String, Object> paramMap = new HashMap<>(16);
        LoginUser loginUser =  new LoginUser();
        loginUser.setUserName(name);
        loginUser.setPassword(pwd);
        loginUser.setLonginDate(new Date());

        try {
            stbMessageHandler.sendMessageToUserByUuid(uuid,loginUser,ConstantValue.WS_COMMAND_LOGIN);
            paramMap.put("code", 200);
            paramMap.put("message", "成功");
        } catch (Exception e) {
            e.printStackTrace();
            paramMap.put("code", 500);
            paramMap.put("message", "失败");
        }

        return paramMap;
    }


    @RequestMapping("logout")
    public String logout(ChannelHandlerContext ct, STBMessage<String> stbMessage) throws Exception {
        System.out.println("指令为------------------2");
        System.out.println("message.getData()接收:" + stbMessage.getData());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("hVender", "default");
        paramMap.put("hModel", "default");
        return gson.toJson(paramMap);
    }

}
