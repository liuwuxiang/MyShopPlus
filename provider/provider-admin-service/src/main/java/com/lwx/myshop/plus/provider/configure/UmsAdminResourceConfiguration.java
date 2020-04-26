package com.lwx.myshop.plus.provider.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 功能描述: 资源服务器
 *
 * @author: 刘武祥
 * @Date: 2020/4/15 0015 11:46
 * @see com.lwx.myshop.plus.provider.configure
 * @version v1.0.0
 */
@Configuration
public class UmsAdminResourceConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
