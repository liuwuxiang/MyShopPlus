package com.lwx.myshop.plus.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 功能描述: 文件上传服务
 *
 * @author: 刘武祥
 * @Date: 2020/4/21 0021 11:35
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CloudUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudUploadApplication.class,args);
    }

}
