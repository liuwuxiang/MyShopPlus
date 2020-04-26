package com.lwx.myshop.plus.business.service;

import com.google.common.collect.Lists;
import com.lwx.myshop.plus.provider.api.UmsAdminService;
import com.lwx.myshop.plus.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 功能描述: 自定义认证授权
 *
 * @author: 刘武祥
 * @Date: 2020/4/15 0015 16:05
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "$2a$10$8bGvURe7Oj7wRKBYjyPEl.H5muLkXHB37dHk3xpgW0uAXWRkqdXlG";

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //1.根据用户名查询用户信息
        UmsAdmin umsAdmin = umsAdminService.get(s);

        //2.给每个用户授予 USER 权限
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        //3.授权
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
        //4.添加到list中
        grantedAuthorities.add(grantedAuthority);

        //5.用户账号存在
        if (umsAdmin != null) {
            return new User(umsAdmin.getUsername(),umsAdmin.getPassword(),grantedAuthorities);
        }

        else {
            return null;
        }

        /*//用户名匹配
        if (s.equals(USERNAME)) {
            List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
            //授权
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
            //添加到list中
            grantedAuthorities.add(grantedAuthority);
            return new User(USERNAME,PASSWORD,grantedAuthorities);
        }

        //用户名不匹配
        else {
            return null;
        }*/
    }

}
