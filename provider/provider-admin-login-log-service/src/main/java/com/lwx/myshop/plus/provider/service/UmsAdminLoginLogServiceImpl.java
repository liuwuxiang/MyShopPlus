package com.lwx.myshop.plus.provider.service;

import com.lwx.myshop.plus.provider.api.UmsAdminLoginLogService;
import com.lwx.myshop.plus.provider.domain.UmsAdminLoginLog;
import com.lwx.myshop.plus.provider.mapper.UmsAdminLoginLogMapper;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * 功能描述: 登录日志实现
 * @author: 刘武祥
 * @Date: 2020/4/23 0023 14:57
 */
@Service(version = "1.0.0")
public class UmsAdminLoginLogServiceImpl implements UmsAdminLoginLogService{

    @Resource
    private UmsAdminLoginLogMapper umsAdminLoginLogMapper;

    @Override
    public int inset(UmsAdminLoginLog umsAdminLoginLog) {
        return umsAdminLoginLogMapper.insert(umsAdminLoginLog);
    }
}
