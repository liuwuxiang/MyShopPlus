package com.lwx.myshop.plus.provider.api;

import com.lwx.myshop.plus.provider.domain.UmsAdminLoginLog;

/**
 * 功能描述: 系统登录日志服务接口
 * @author: 刘武祥
 * @Date: 2020/4/23 0023 14:57
 */
public interface UmsAdminLoginLogService{

    /**
     * 功能描述: 新增日志
     *
     * @param umsAdminLoginLog {@link UmsAdminLoginLog}
     * @return int             {@code int} 大于 0 则表示添加成功
     * @auther 刘武祥
     * @date 2020/4/23 0023 15:10
     */
    int inset(UmsAdminLoginLog umsAdminLoginLog);

}
