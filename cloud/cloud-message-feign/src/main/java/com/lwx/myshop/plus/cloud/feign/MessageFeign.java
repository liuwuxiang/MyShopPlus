package com.lwx.myshop.plus.cloud.feign;

import com.lwx.myshop.plus.cloud.dto.UmsAdminLoginLogDTO;
import com.lwx.myshop.plus.cloud.feign.fallback.MessageFeignFallback;
import com.lwx.myshop.plus.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 功能描述: FeignClient
 *
 * @author: 刘武祥
 * @Date: 2020/4/23 0023 16:28
 */
@FeignClient(value = "cloud-message",path = "message",configuration = FeignRequestConfiguration.class,fallback = MessageFeignFallback.class)
public interface MessageFeign {

    @PostMapping(value = "admin/login/log")
    public String sendAdminLoginLog(@RequestBody UmsAdminLoginLogDTO dto);

}
