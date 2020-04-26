package com.lwx.myshop.plus.cloud.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述: 登录日志数据传输对象
 *
 * @author: 刘武祥
 * @Date: 2020/4/23 0023 16:22
 */
@Data
public class UmsAdminLoginLogDTO implements Serializable {
    private static final long serialVersionUID = 4445308092932725633L;
    private Long id;
    private Long adminId;
    private Date createTime;
    private String ip;
    private String address;
    private String userAgent;
}
