package com.cmcc.hsp.netty;


import com.cmcc.hsp.netty.server.STBServerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhouzhou
 *
 */
@SpringBootApplication
public class HspNettyServerApplication implements CommandLineRunner {

    @Autowired
    STBServerListener stbServerListener;

    public static void main(String[] args) {
        SpringApplication.run(HspNettyServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        stbServerListener.start();
    }
}
