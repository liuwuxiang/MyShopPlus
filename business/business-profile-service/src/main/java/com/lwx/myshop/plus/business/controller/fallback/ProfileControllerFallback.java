package com.lwx.myshop.plus.business.controller.fallback;

import com.lwx.myshop.plus.business.dto.UmsAdminDTO;
import com.lwx.myshop.plus.business.feign.fallback.ProfileFeignFallback;
import com.lwx.myshop.plus.commons.dto.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能描述: 用户管理服务熔断器
 *
 * @author: 刘武祥
 * @Date: 2020/4/22 0022 17:14
 */
public class ProfileControllerFallback {

    private static final Logger logger = LoggerFactory.getLogger(ProfileControllerFallback.class);

    /**
     * 功能描述: 熔断方法
     *          必须加static
     *
     * @param username  {@code String}      用户名
     * @param ex        {@code Throwable}   异常信息
     * @return ResponseResult<UmsAdminDTO> {@link ResponseResult} 返回结果集
     * @auther 刘武祥
     * @date 2020/4/22 0022 17:27
     */
    public static ResponseResult<UmsAdminDTO> infoFallback(String username,Throwable ex){
        logger.warn("invoke infoFallback" + ex.getClass().getTypeName());
        return new ResponseResult<UmsAdminDTO>(ResponseResult.CodeStatus.BREAKING, ProfileFeignFallback.BREAKING_MESSAGE);
    }

}
