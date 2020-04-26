package com.lwx.myshop.plus.business.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 功能描述: 登录参数
 *
 * @author: 刘武祥
 * @Date: 2020/4/16 0016 09:59
 */
@Data
public class LoginParam implements Serializable {

    private String username;
    private String password;

}
