package com.lwx.myshop.plus.cloud.feign.fallback;

import com.lwx.myshop.plus.cloud.dto.UmsAdminLoginLogDTO;
import com.lwx.myshop.plus.cloud.feign.MessageFeign;
import org.springframework.stereotype.Component;

/**
 * 功能描述: feign 熔断器
 *
 * @author: 刘武祥
 * @Date: 2020/4/23 0023 16:33
 */
@Component
public class MessageFeignFallback implements MessageFeign {

    private static final String BREAKING_MESSAGE = "请求失败了，请检查您的网络";

    @Override
    public String sendAdminLoginLog(UmsAdminLoginLogDTO dto) {
        return null;
    }
}
