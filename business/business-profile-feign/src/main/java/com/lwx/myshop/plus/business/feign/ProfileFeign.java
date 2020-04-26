package com.lwx.myshop.plus.business.feign;

import com.lwx.myshop.plus.business.dto.params.IconParam;
import com.lwx.myshop.plus.business.dto.params.PasswordParam;
import com.lwx.myshop.plus.business.dto.params.ProfileParam;
import com.lwx.myshop.plus.business.feign.fallback.ProfileFeignFallback;
import com.lwx.myshop.plus.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 功能描述: Feign客户端
 *          1.@FeignClient(value = "business-profile",path = "profile")
 *            value = "business-profile" 表示找到服务名为 business-profile 的服务 , path = "profile" 路径为 profile
 *
 * @author: 刘武祥
 * @Date: 2020/4/17 0017 15:25
 */
@FeignClient(value = "business-profile",path = "profile",configuration = FeignRequestConfiguration.class,fallback = ProfileFeignFallback.class)
public interface ProfileFeign {

    /**
     * 功能描述: 查询用户信息
     *
     * @param username  用户名
     * @return String
     * @auther 刘武祥
     * @date 2020/4/17 0017 15:21
     */
    @GetMapping(value = "info/{username}")
    String info(@PathVariable String username);

    /**
     * 功能描述: 用户更新（非幂等性操作）
     *
     * @param profileParam {@link ProfileParam} 个人信息参数
     * @return string
     * @auther 刘武祥
     * @date 2020/4/20 0020 16:44
     */
    @PostMapping(value = "update")
    String update(@RequestBody ProfileParam profileParam);

    /**
     * 功能描述: 修改密码
     *
     * @param passwordParam {@link PasswordParam} 密码参数
     * @return string {@code String}
     * @auther 刘武祥
     * @date 2020/4/21 0021 14:48
     */
    @PostMapping(value = "modify/password")
    String modifyPassword(@RequestBody PasswordParam passwordParam);

    /**
     * 功能描述: 修改头像
     *
     * @param iconParam {@link IconParam} 头像参数
     * @return int      {@code int}    大于0 表示更新成功
     * @auther 刘武祥
     * @date 2020/4/21 0021 14:31
     */
    @PostMapping(value = "modify/icon")
    String modifyIcon(@RequestBody IconParam iconParam);

}
