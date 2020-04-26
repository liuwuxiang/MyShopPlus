package com.lwx.myshop.plus.provider.api;

import com.lwx.myshop.plus.provider.domain.UmsAdmin;

/**
 * 功能描述：用户管理服务
 * @author 刘武祥
 */
public interface UmsAdminService {

    /**
     * 功能描述: 新增用户
     *
     * @param umsAdmin  {@link UmsAdmin}
     * @return int  大于 0 表示新增成功
     * @auther 刘武祥
     * @date 2020/4/15 0015 11:04
     */
    int insert(UmsAdmin umsAdmin);

    /**
     * 功能描述: 根据用户名查询获取用户
     *
     * @param username 用户名
     * @return {@link UmsAdmin}
     * @auther 刘武祥
     * @date 2020/4/15 0015 14:16
     */
    UmsAdmin get(String username);

    /**
     * 功能描述: 获取用户
     *
     * @param umsAdmin {@link UmsAdmin}
     * @return {@link UmsAdmin}
     * @auther 刘武祥
     * @date 2020/4/15 0015 14:21
     */
    UmsAdmin get(UmsAdmin umsAdmin);

    /**
     * 功能描述: 更新用户
     *
     * @param umsAdmin {@link UmsAdmin} 用户实体类
     * @return int
     * @auther 刘武祥
     * @date 2020/4/20 0020 16:11
     */
    int update(UmsAdmin umsAdmin);

    /**
     * 功能描述: 修改密码
     *
     * @param username {@code String} 用户名
     * @param password {@code String} 明文密码
     * @return int     {@code int} 大于 0 则表示更新成功
     * @auther 刘武祥
     * @date 2020/4/21 0021 15:08
     */
    int modifyPassword(String username,String password);

    /**
     * 功能描述: 修改头像
     *
     * @param username {@code String} 用户名
     * @param path     {@code String} 头像地址
     * @return int     {@code int}    大于0 表示更新成功
     * @auther 刘武祥
     * @date 2020/4/21 0021 14:31
     */
    int modifyIcon(String username,String path);

}
