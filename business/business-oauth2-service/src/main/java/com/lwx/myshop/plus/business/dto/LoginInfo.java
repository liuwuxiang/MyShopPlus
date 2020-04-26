package com.lwx.myshop.plus.business.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 功能描述: 登录信息
 *
 * @author: 刘武祥
 * @Date: 2020/4/16 0016 17:29
 */
@Data
public class LoginInfo implements Serializable {

    private String name;
    private String avatar;
    private String nickName;

}
