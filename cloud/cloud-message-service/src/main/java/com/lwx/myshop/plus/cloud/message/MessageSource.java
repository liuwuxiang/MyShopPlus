package com.lwx.myshop.plus.cloud.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 功能描述: 信息来源
 *
 * @author: 刘武祥
 * @Date: 2020/4/23 0023 16:53
 */
public interface MessageSource {

    /**
     * 功能描述: 消息来源主题
     *
     * @return {@link MessageChannel}
     * @auther 刘武祥
     * @date 2020/4/23 0023 16:56
     */
    @Output("admin-login-log-topic")
    MessageChannel adminLoginLog();

}
