package com.lwx.myshop.plus.cloud;

import com.lwx.myshop.plus.cloud.message.MessageSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * 功能描述: 消息队列服务
 *
 * @author: 刘武祥
 * @Date: 2020/4/23 0023 16:43
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(MessageSource.class)
public class CloudMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudMessageApplication.class,args);
    }

}
