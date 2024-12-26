package com.cmcc.hsp.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 添加配置
 * @author zhouzhou
 */
@Configuration
public class ApplicationConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations("file:/Users/zhouzhou/Downloads");
        super.addResourceHandlers(registry);
    }
}
