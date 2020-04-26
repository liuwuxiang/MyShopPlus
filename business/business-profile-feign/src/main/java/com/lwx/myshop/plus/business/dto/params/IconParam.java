package com.lwx.myshop.plus.business.dto.params;

import lombok.Data;

import java.io.Serializable;

/**
 * 功能描述: 头像参数数据传输对象
 *
 * @author: 刘武祥
 * @Date: 2020/4/21 0021 14:50
 */
@Data
public class IconParam implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像路径
     */
    private String path;

}
