package com.cmcc.hsp.client;


import com.cmcc.hsp.client.netty.NettyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhouzhou
 */
@SpringBootApplication
public class HspNettyClientApplication implements CommandLineRunner {

    @Autowired
    NettyClient nettyClient;

    public static void main(String[] args) {
        SpringApplication.run(HspNettyClientApplication.class, args);
    }


    /**
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        nettyClient.init();
    }
}
