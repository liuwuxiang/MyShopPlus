package com.lwx.myshop.plus.business;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 功能描述: 测试
 *
 * @author: 刘武祥
 * @Date: 2020/4/15 0015 16:11
 */
@SpringBootTest
public class BusinessOAuth2Tests {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void testPasswordEncoder(){
        System.out.println(passwordEncoder.encode("123456"));
    }

}
