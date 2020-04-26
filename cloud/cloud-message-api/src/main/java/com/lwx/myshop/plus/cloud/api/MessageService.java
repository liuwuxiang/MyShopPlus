package com.lwx.myshop.plus.cloud.api;

import com.lwx.myshop.plus.cloud.dto.UmsAdminLoginLogDTO;

/**
 * 功能描述: 系统登录日志服务接口
 *
 * @author: 刘武祥
 * @Date: 2020/4/24 0024 15:34
 */
public interface MessageService {

    boolean sendAdminLoginLog(UmsAdminLoginLogDTO umsAdminLoginLogDTO);

}
