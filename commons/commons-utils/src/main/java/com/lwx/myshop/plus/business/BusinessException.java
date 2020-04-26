package com.lwx.myshop.plus.business;

/**
 * 功能描述: 全局业务异常
 *
 * @author: 刘武祥
 * @Date: 2020/4/24 0024 12:01
 */
public class BusinessException extends RuntimeException{

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BusinessException() {

    }

    public BusinessException(BusinessStatus status) {
        super(status.getMessage());
        this.code = status.getCode();
    }
}
