package com.lwx.myshop.plus.provider.tests;
import com.lwx.myshop.plus.provider.api.UmsAdminLoginLogService;
import com.lwx.myshop.plus.provider.domain.UmsAdminLoginLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 功能描述: 测试
 *
 * @author: 刘武祥
 * @Date: 2020/4/23 0023 15:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class UmsAdminLoginLogTests {

    @Resource
    private UmsAdminLoginLogService umsAdminLoginLogService;

    @Test
    public void testInsert(){
        UmsAdminLoginLog umsAdminLoginLog = new UmsAdminLoginLog();
        umsAdminLoginLog.setAdminId(1L);
        umsAdminLoginLog.setCreateTime(new Date());
        umsAdminLoginLog.setIp("0.0.0.0");
        umsAdminLoginLog.setAddress("0.0.0.0");
        umsAdminLoginLog.setUserAgent("0.0.0.0");
        umsAdminLoginLogService.inset(umsAdminLoginLog);
    }

}
