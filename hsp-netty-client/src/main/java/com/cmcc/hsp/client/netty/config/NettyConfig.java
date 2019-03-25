package com.cmcc.hsp.client.netty.config;

import com.cmcc.hsp.client.netty.NettyClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettyConfig {
    /**
     * 主机
     */
    @Value("${netty.host}")
    private String host;
    /**
     * 端口号
     */
    @Value("${netty.port}")
    private int port;
}
