package com.lwx.myshop.plus.configuration;

import com.lwx.myshop.plus.interceptor.FeignRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述: Feign 全局配置
 *
 * @author: 刘武祥
 * @version v1.0.0
 * @Date: 2020/4/17 0017 17:24
 * @see com.lwx.myshop.plus.configuration
 */
@Configuration
public class FeignRequestConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignRequestInterceptor();
    }

}
