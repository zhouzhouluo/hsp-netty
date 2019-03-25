package com.cmcc.hsp.netty.action;

import com.cmcc.hsp.netty.annotation.STBRequestMapping;
import com.cmcc.hsp.netty.annotation.STBRequestMappingController;
import com.cmcc.hsp.netty.model.ConstantValue;
import com.cmcc.hsp.netty.model.LoginUser;
import com.cmcc.hsp.netty.model.STBMessage;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;

@STBRequestMappingController()
public class UserAction {

    Gson gson = new Gson();

    @STBRequestMapping(ConstantValue.WS_COMMAND_LOGIN)
    public String login(ChannelHandlerContext ct, STBMessage<LoginUser> stbMessage) {
        System.out.println("message.getData()接收:" + stbMessage.fromJson(LoginUser.class));
        LoginUser loginUser = stbMessage.fromJson(LoginUser.class);
        Map<String, Object> paramMap = new HashMap<>(16);
        paramMap.put("getPassword", loginUser.getPassword());
        paramMap.put("getUserName", loginUser.getUserName());
        System.out.println("gson.toJson(paramMap):"+gson.toJson(paramMap));
        return gson.toJson(paramMap);
    }


    @STBRequestMapping(ConstantValue.WS_COMMAND_LOGOUT)
    public String test(ChannelHandlerContext ct, STBMessage<String> stbMessage) throws Exception {
        System.out.println("指令为------------------2");
        System.out.println("message.getData()接收:" + stbMessage.getData());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("hVender", "default");
        paramMap.put("hModel", "default");
        return gson.toJson(paramMap);
    }

}
