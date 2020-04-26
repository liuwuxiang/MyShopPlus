package com.lwx.myshop.plus.business.controller;

import com.lwx.myshop.plus.commons.dto.ResponseResult;
import com.lwx.myshop.plus.provider.api.UmsAdminService;
import com.lwx.myshop.plus.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述: 用户注册.
 *          1.@CrossOrigin(origins = "*",maxAge = 3600) 解决跨域 超时时间3600秒. 路由网关已配置
 *
 * @author: 刘武祥
 * @Date: 2020/4/15 0015 12:05
 */
@RestController
@RequestMapping(value = "reg")
public class RegController {

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

    /**
     * 功能描述: 注册
     *          1.@PostMapping(value = "") 非幂等性请求
     *
     * @param umsAdmin  {@link UmsAdmin}
     * @return ResponseResult<UmsAdmin> {@link ResponseResult}
     * @auther 刘武祥
     * @date 2020/4/15 0015 14:07
     */
    @PostMapping(value = "")
    public ResponseResult<UmsAdmin> reg(@RequestBody UmsAdmin umsAdmin){
        //1.注册信息验证
        String message = validateReg(umsAdmin);

        //2.通过验证  则注册
        if (message == null) {
            //2.1 插入
            int result = umsAdminService.insert(umsAdmin);
            //2.2 根据用户名获取到用户信息
            UmsAdmin admin = umsAdminService.get(umsAdmin.getUsername());

            //2.3 大于 0  注册成功
            if (result > 0 ){
                return new ResponseResult<UmsAdmin>(HttpStatus.OK.value(), "用户注册成功",admin);
            }
        }

        return new ResponseResult<UmsAdmin>(HttpStatus.NOT_ACCEPTABLE.value(), message != null ? message:"用户注册失败");
    }

    /**
     * 功能描述: 注册信息验证
     *
     * @param umsAdmin {@link UmsAdmin}
     * @return String {@code String} 验证结果
     * @auther 刘武祥
     * @date 2020/4/15 0015 14:10
     */
    private String validateReg(UmsAdmin umsAdmin) {
        //1.根据用户名查询用户是否重复
        UmsAdmin admin = umsAdminService.get(umsAdmin.getUsername());

        //2.用户名不等于null
        if (admin != null) {
            return "用户名已存在，请重新输入";
        }

        return null;
    }

}
