package com.lwx.myshop.plus.message.consumer;

import com.lwx.myshop.plus.commons.utils.MapperUtils;
import com.lwx.myshop.plus.provider.api.UmsAdminLoginLogService;
import com.lwx.myshop.plus.provider.domain.UmsAdminLoginLog;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * 功能描述: 管理员登录日志接收
 *
 * @author: 刘武祥
 * @Date: 2020/4/24 0024 14:57
 */
public class AdminLoginLogReceive {

    @Reference(version = "1.0.0")
    private UmsAdminLoginLogService umsAdminLoginLogService;

    @StreamListener("admin-login-log-topic")
    public void receiveAdminLoginLog(String umsAdminLoginLogJson) throws Exception {
        //json字符串转java对象
        UmsAdminLoginLog umsAdminLoginLog = MapperUtils.json2pojo(umsAdminLoginLogJson, UmsAdminLoginLog.class);
        umsAdminLoginLogService.inset(umsAdminLoginLog);
    }
}
