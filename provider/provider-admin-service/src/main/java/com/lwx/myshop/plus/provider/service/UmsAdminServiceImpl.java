package com.lwx.myshop.plus.provider.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lwx.myshop.plus.provider.api.UmsAdminService;
import com.lwx.myshop.plus.provider.domain.UmsAdmin;
import com.lwx.myshop.plus.provider.mapper.UmsAdminMapper;
import com.lwx.myshop.plus.provider.service.fallback.UmsAdminServiceFallback;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 功能描述: 用户管理服务 业务逻辑实现
 *
 * @author 刘武祥
 * @Date: 2020/4/15 0015 11:05
 */
@Service(version = "1.0.0")
public class UmsAdminServiceImpl implements UmsAdminService {

    @Resource
    private UmsAdminMapper umsAdminMapper;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 功能描述: 新增用户
     *
     * @param umsAdmin 系统用户实体类
     * @return int
     * @auther 刘武祥
     * @date 2020/4/15 0015 11:10
     */
    @Override
    public int insert(UmsAdmin umsAdmin) {
        //初始化用户对象
        initUmsAdmin(umsAdmin);
        return umsAdminMapper.insert(umsAdmin);
    }

    /**
     * 功能描述: 根据用户名查询获取 用户信息
     *          熔断器的使用
     *          1.@SentinelResource 注解指定该类中的方法即可实现熔断降级功能
     * <p>
     * 1.  {@link SentinelResource#value()} 对应的是 Sentinel 控制台中的资源，可用作控制台设置【流控】和【降级】操作 <br>
     * 2.  {@link SentinelResource#fallback()} 对应的是 {@link UmsAdminServiceFallback#getByUsernameFallback(String, Throwable)}，并且必须为 `static` <br>
     * 3. 如果不设置 {@link SentinelResource#fallbackClass()}，则需要在当前类中创建一个 `Fallback` 函数，函数签名与原函数一致或加一个 {@link Throwable} 类型的参数
     * </p>
     * @param username {@code String} 用户名
     * @return {@link UmsAdmin}
     * @auther 刘武祥
     * @date 2020/4/15 0015 14:16
     */
    @Override
    @SentinelResource(value = "getUsername",fallback = "getByUsernameFallback",fallbackClass = UmsAdminServiceFallback.class)
    public UmsAdmin get(String username) {
        // 增加一段异常代码，用于测试熔断
        /*if ("admin".equals(username)) {
            throw new IllegalArgumentException("invalid arg");
        }*/
        Example example = new Example(UmsAdmin.class);
        //创建查询条件
        example.createCriteria().andEqualTo("username",username);
        return umsAdminMapper.selectOneByExample(example);
    }

    /**
     * 功能描述: 获取用户 (根据对象查询)
     *
     * @param umsAdmin {@link UmsAdmin}
     * @return {@link UmsAdmin} 系统用户实体类
     * @auther 刘武祥
     * @date 2020/4/15 0015 14:21
     */
    @Override
    public UmsAdmin get(UmsAdmin umsAdmin) {
        return umsAdminMapper.selectOne(umsAdmin);
    }

    /**
     * 功能描述: 更新用户
     *
     * @param umsAdmin {@link UmsAdmin} 用户实体类
     * @return int     {@code int   } 大于 0 则表示更新成功
     * @auther 刘武祥
     * @date 2020/4/20 0020 16:11
     */
    @Override
    public int update(UmsAdmin umsAdmin) {
        //1.数据库里查询得到原始对象的用户信息 (因为只能更新四个字段)
        UmsAdmin oldUmsAdmin = get(umsAdmin.getUsername());
        //2.设置更新的四个字段(仅更新 邮箱、昵称、备注、状态)
        oldUmsAdmin.setEmail(umsAdmin.getEmail());
        oldUmsAdmin.setNickName(umsAdmin.getNickName());
        oldUmsAdmin.setNote(umsAdmin.getNote());
        oldUmsAdmin.setStatus(umsAdmin.getStatus());

        //3.根据主键更新
        return umsAdminMapper.updateByPrimaryKey(oldUmsAdmin);
    }

    /**
     * 功能描述: 修改密码
     *
     * @param username {@code String} 用户名
     * @param password {@code String} 密码
     * @return int     {@code int   } 大于 0 则表示更新成功
     * @auther 刘武祥
     * @date 2020/4/21 0021 15:08
     */
    @Override
    public int modifyPassword(String username, String password) {
        //1.数据库根据用户名查询到 用户信息
        UmsAdmin umsAdmin = get(username);
        //2.修改密码
        umsAdmin.setPassword(passwordEncoder.encode(password));
        return umsAdminMapper.updateByPrimaryKey(umsAdmin);
    }

    /**
     * 功能描述: 修改头像
     *
     * @param username {@code String} 用户名
     * @param path     {@code String} 头像路径
     * @return int     {@code int}    大于0 表示更新成功
     * @auther 刘武祥
     * @date 2020/4/21 0021 14:31
     */
    @Override
    public int modifyIcon(String username, String path) {
        //1.根据用户名查询到 用户信息
        UmsAdmin umsAdmin = get(username);
        //2.修改头像
        umsAdmin.setIcon(path);
        return umsAdminMapper.updateByPrimaryKey(umsAdmin);
    }

    /**
     * 功能描述: 初始化用户对象
     *
     * @param umsAdmin  {@link UmsAdmin}
     * @return void
     * @auther 刘武祥
     * @date 2020/4/15 0015 14:30
     */
    private void initUmsAdmin(UmsAdmin umsAdmin){
        //1.初始化时间
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setLoginTime(new Date());

        //2.初始化状态 0为禁用
        if (umsAdmin.getStatus() == null) {
            umsAdmin.setStatus(0);
        }

        //3.密码加密
        umsAdmin.setPassword(passwordEncoder.encode(umsAdmin.getPassword()));
    }

}
