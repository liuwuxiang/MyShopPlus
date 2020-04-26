package com.lwx.myshop.plus.business.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lwx.myshop.plus.business.controller.fallback.ProfileControllerFallback;
import com.lwx.myshop.plus.business.dto.UmsAdminDTO;
import com.lwx.myshop.plus.business.dto.params.IconParam;
import com.lwx.myshop.plus.business.dto.params.PasswordParam;
import com.lwx.myshop.plus.business.dto.params.ProfileParam;
import com.lwx.myshop.plus.commons.dto.ResponseResult;
import com.lwx.myshop.plus.provider.api.UmsAdminService;
import com.lwx.myshop.plus.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 功能描述: 个人信息管理
 *
 * @author: 刘武祥
 * @Date: 2020/4/17 0017 15:14
 */
@RestController
@RequestMapping(value = "profile")
public class ProfileController {

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;


    /**
     * 功能描述: 查询用户信息
     *
     * @param username  用户名
     * @return {@link ResponseResult} 结果集
     * @auther 刘武祥
     * @date 2020/4/17 0017 15:21
     */
    @GetMapping(value = "info/{username}")
    @SentinelResource(value = "info",fallback = "infoFallback",fallbackClass = ProfileControllerFallback.class)
    public ResponseResult<UmsAdminDTO> info(@PathVariable String username){
        UmsAdmin umsAdmin = umsAdminService.get(username);

        UmsAdminDTO dto = new UmsAdminDTO();
        //将原数据拷贝到dto里
        BeanUtils.copyProperties(umsAdmin,dto);
        return new ResponseResult<UmsAdminDTO>(ResponseResult.CodeStatus.OK,"查询用户信息成功",dto);
    }

    /**
     * 功能描述: 更新用户个人信息
     *
     * @param profileParam {@link ProfileParam} 用户个人信息
     * @return com.lwx.myshop.plus.commons.dto.ResponseResult<java.lang.Void> {@link ResponseResult} 结果集
     * @auther 刘武祥
     * @date 2020/4/20 0020 16:52
     */
    @PostMapping(value = "update")
    public ResponseResult<Void> update(@RequestBody ProfileParam profileParam) {
        //1.前端传过来的是profileParam参数json字符串 但传进数据访问层的是umsAdmin对象
        UmsAdmin newUmsAdmin = new UmsAdmin();
        //2.将前端参数拷贝进umsAdmin对象里 才可更新
        BeanUtils.copyProperties(profileParam,newUmsAdmin);
        int result = umsAdminService.update(newUmsAdmin);

        //3.成功
        if (result > 0){
            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"更新个人信息成功");
        }

        //4.失败
        else {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"更新个人信息失败");
        }
    }

    /**
     * 功能描述: 修改密码
     *
     * @param passwordParam {@link PasswordParam} 密码参数
     * @return com.lwx.myshop.plus.commons.dto.ResponseResult<java.lang.Void> {@link ResponseResult} 返回结果集
     * @auther 刘武祥
     * @date 2020/4/21 0021 15:27
     */
    @PostMapping(value = "modify/password")
    public ResponseResult<Void> modifyPassword(@RequestBody PasswordParam passwordParam) {
        //1.数据库根据 用户名 查询 用户信息
        UmsAdmin umsAdmin = umsAdminService.get(passwordParam.getUsername());

        //2.旧密码比对正确 旧密码与数据库密码匹配
        if (passwordEncoder.matches(passwordParam.getOldPassword(),umsAdmin.getPassword())) {
            //修改密码
            int result = umsAdminService.modifyPassword(umsAdmin.getUsername(), passwordParam.getNewPassword());
            if (result > 0) {
                return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"修改密码成功");
            }
        }

        //3.旧密码比对错误
        else {
            new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"旧密码错误,请重新输入");
        }

        return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"修改密码失败");
    }

    /**
     * 功能描述: 修改头像
     *
     * @param iconParam {@link IconParam} 头像参数数据传输对象
     * @return com.lwx.myshop.plus.commons.dto.ResponseResult<java.lang.Void> {@link ResponseResult} 返回结果集
     * @auther 刘武祥
     * @date 2020/4/21 0021 16:12
     */
    @PostMapping(value = "modify/icon")
    public ResponseResult<Void> modifyIcon(@RequestBody IconParam iconParam) {

        int result = umsAdminService.modifyIcon(iconParam.getUsername(), iconParam.getPath());

        if (result > 0) {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"头像修改成功");
        }

        else {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL,"头像修改失败");
        }
    }

}
