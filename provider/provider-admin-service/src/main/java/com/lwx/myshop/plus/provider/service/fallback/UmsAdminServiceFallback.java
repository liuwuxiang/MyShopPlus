package com.lwx.myshop.plus.provider.service.fallback;

import com.lwx.myshop.plus.provider.domain.UmsAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能描述: 用户服务提供者熔断器
 *
 * @author: 刘武祥
 * @Date: 2020/4/22 0022 16:45
 * @version v1.0.0
 * @see com.lwx.myshop.plus.provider.service.fallback
 */
public class UmsAdminServiceFallback {

    private static final Logger logger = LoggerFactory.getLogger(UmsAdminServiceFallback.class);

    /**
     * 功能描述: 熔断方法
     *
     * @param username  {@code String}      用户名
     * @param ex        {@code Throwable}   异常信息
     * @return com.lwx.myshop.plus.provider.domain.UmsAdmin {@link UmsAdmin}
     * @auther 刘武祥
     * @date 2020/4/22 0022 16:55
     */
    public static UmsAdmin getByUsernameFallback(String username, Throwable ex) {
        logger.warn("invoke getByUsernameFallback " + ex.getClass().getTypeName());
        return null;
    }
}
