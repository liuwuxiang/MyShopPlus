package com.lwx.myshop.plus.business.dto.params;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述: 个人信息参数
 *
 * @author: 刘武祥
 * @Date: 2020/4/20 0020 16:26
 */
@Data
public class ProfileParam implements Serializable {

    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像
     */
    private String icon;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 备注信息
     */
    private String note;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后登录时间
     */
    private Date loginTime;

    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    private Integer status;

}
