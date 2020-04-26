package com.lwx.myshop.plus.cloud.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述: 系统登录日志传输对象
 *
 * @author: 刘武祥
 * @Date: 2020/4/24 0024 15:38
 */
@Data
public class UmsAdminLoginLogDTO implements Serializable {

    private static final long serialVersionUID = -673155421177717706L;

    private Long id;
    private Long adminId;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String ip;
    private String address;
    private String userAgent;

}
