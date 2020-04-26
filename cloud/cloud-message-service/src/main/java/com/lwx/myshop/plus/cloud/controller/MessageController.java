package com.lwx.myshop.plus.cloud.controller;

import com.lwx.myshop.plus.cloud.dto.UmsAdminLoginLogDTO;
import com.lwx.myshop.plus.cloud.producer.MessageProducer;
import com.lwx.myshop.plus.commons.dto.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 功能描述: 登录日志管理
 *
 * @author: 刘武祥
 * @Date: 2020/4/23 0023 17:08
 */
@RestController
@RequestMapping(value = "message")
public class MessageController {

    @Resource
    private MessageProducer producer;

    /**
     * 功能描述: 登录日志管理
     *
     * @param dto   {@link UmsAdminLoginLogDTO} 登录日志数据传输对象
     * @return ResponseResult<Void> {@link ResponseResult} 返回结果集
     * @auther 刘武祥
     * @date 2020/4/23 0023 17:19
     */
    @PostMapping(value = "admin/login/log")
    public ResponseResult<Void> sendAdminLoginLog(@RequestBody UmsAdminLoginLogDTO dto){
        boolean flag = producer.sendAdminLoginLog(dto);

        //发送成功
        if (flag) {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "发送管理员登录日志成功");
        }

        //发送失败
        else {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "发送管理员登录日志失败");
        }
    }

}
