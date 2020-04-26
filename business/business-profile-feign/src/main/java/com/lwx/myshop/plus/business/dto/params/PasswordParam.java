package com.lwx.myshop.plus.business.dto.params;

import lombok.Data;

/**
 * 功能描述:
 *
 * @author: 刘武祥
 * @Date: 2020/4/21 0021 14:59
 */
@Data
public class PasswordParam {

    private String username;
    private String newPassword;
    private String oldPassword;

}
