package com.lwx.myshop.plus.cloud.producer;

import com.lwx.myshop.plus.cloud.dto.UmsAdminLoginLogDTO;
import com.lwx.myshop.plus.cloud.message.MessageSource;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 功能描述: 消息生产者 专门发消息
 *
 * @author: 刘武祥
 * @version v1.0.0
 * @Date: 2020/4/23 0023 16:58
 * @see com.lwx.myshop.plus.cloud.producer
 */
@Component
@Service(version = "1.0.0")
public class MessageProducer {

    @Resource
    private MessageSource source;

    /**
     * 功能描述: 管理登录日志
     *
     * @param dto   {@link UmsAdminLoginLogDTO} 登录日志传输对象
     * @return boolean true/false
     * @auther 刘武祥
     * @date 2020/4/23 0023 17:06
     */
    public boolean sendAdminLoginLog(UmsAdminLoginLogDTO dto){
        return source.adminLoginLog().send(MessageBuilder.withPayload(dto).build());
    }

}
