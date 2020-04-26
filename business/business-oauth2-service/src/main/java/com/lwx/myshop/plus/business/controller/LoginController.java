package com.lwx.myshop.plus.business.controller;

import com.google.common.collect.Maps;
import com.lwx.myshop.plus.business.BusinessException;
import com.lwx.myshop.plus.business.BusinessStatus;
import com.lwx.myshop.plus.business.dto.LoginInfo;
import com.lwx.myshop.plus.business.dto.LoginParam;
import com.lwx.myshop.plus.business.feign.ProfileFeign;
import com.lwx.myshop.plus.commons.dto.ResponseResult;
import com.lwx.myshop.plus.commons.utils.MapperUtils;
import com.lwx.myshop.plus.commons.utils.OkHttpClientUtil;
import com.lwx.myshop.plus.provider.domain.UmsAdmin;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * 功能描述: 登录管理
 *          1.@CrossOrigin(origins = "*",maxAge = 3600) 路由网关已配置防止冲突
 *
 * @author: 刘武祥
 * @Date: 2020/4/16 0016 09:57
 */
@RestController
public class LoginController {

    private static final String URL_OAUTH_TOKEN = "http://localhost:9001/oauth/token";

    @Value("${business.oauth2.grant_type}")
    public String oauth2GrantType;

    @Value("${business.oauth2.client_id}")
    public String oauth2ClientId;

    @Value("${business.oauth2.client_secret}")
    public String oauth2ClientSecret;

    @Resource(name = "userDetailsServiceBean")
    public UserDetailsService userDetailsService;

    @Resource
    public BCryptPasswordEncoder passwordEncoder;

    /**
     * token存储
     */
    @Resource
    public TokenStore tokenStore;

    @Resource
    private ProfileFeign profileFeign;

    /**
     * 功能描述: 登录
     *
     * @param loginParam {@link LoginParam} 登录参数
     * @return  {@link ResponseResult} 结果集
     * @auther 刘武祥
     * @date 2020/4/16 0016 17:21
     */
    @PostMapping(value = "/user/login")
    public ResponseResult<Map<String,Object>> login(@RequestBody LoginParam loginParam) throws Exception{
        //1.封装返回结果集
        Map<String,Object> result = Maps.newHashMap();

        //2.验证账号密码
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginParam.getUsername());
        if (userDetails == null || !passwordEncoder.matches(loginParam.getPassword(),userDetails.getPassword())){
            throw new BusinessException(BusinessStatus.ADMIN_PASSWORD);
            //return new ResponseResult<Map<String, Object>>(ResponseResult.CodeStatus.ILLEGAL_REQUEST,"账号或密码错误",null);
        }

        //3.通过 HTTP 客户端请求登录接口
        Map<String,String> params = Maps.newHashMap();
        params.put("username",loginParam.getUsername());
        params.put("password",loginParam.getPassword());
        //密码模式
        params.put("grant_type",oauth2GrantType);
        params.put("client_id",oauth2ClientId);
        params.put("client_secret", oauth2ClientSecret);

        try {
            //单例模式
            Response response = OkHttpClientUtil.getInstance().postData(URL_OAUTH_TOKEN, params);
            //json数据
            String jsonString = Objects.requireNonNull(response.body()).string();
            //json转mapper
            Map<String, Object> jsonMap = MapperUtils.json2map(jsonString);
            String token = String.valueOf(jsonMap.get("access_token"));
            result.put("token",token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseResult<Map<String,Object>>(ResponseResult.CodeStatus.OK,"登录成功",result);
    }

    /**
     * 功能描述: 获取用户信息
     *
     * @return {@link ResponseResult} 结果集
     * @auther 刘武祥
     * @date 2020/4/16 0016 17:41
     */
    @GetMapping(value = "/user/info")
    public ResponseResult<LoginInfo> userInfo() throws Exception {
        //1.安全上下文中 -> 得到认证信息 -> authentication.getName()得到用户名
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //2.用户信息json字符串
        String jsonString = profileFeign.info(authentication.getName());
        //3.转对象
        UmsAdmin umsAdmin = MapperUtils.json2pojoByTree(jsonString, "data", UmsAdmin.class);

        //4.按照熔断器给到的结果，此时 umsAdmin 为空，我们需要直接将熔断结果返回给客户端
        if (umsAdmin.getUsername() == null) {
            return MapperUtils.json2pojo(jsonString,ResponseResult.class);
        }

        //5..封装并返回结果 设置用户名
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setName(umsAdmin.getUsername());
        loginInfo.setAvatar(umsAdmin.getIcon());
        loginInfo.setAvatar(umsAdmin.getNickName());
        return new ResponseResult<LoginInfo>(ResponseResult.CodeStatus.OK,"获取用户信息成功",loginInfo);
    }

    /**
     * 功能描述: 注销
     *
     * @param request 请求
     * @return {@link ResponseResult} 结果集
     * @auther 刘武祥
     * @date 2020/4/17 0017 10:41
     */
    @PostMapping(value = "/user/logout")
    public ResponseResult<Void> logout(HttpServletRequest request) {
        //1.获取请求中的token名称
        String token = request.getParameter("access_token");
        //2.token存储中读取出token
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        //3.token存储中删除token
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"用户注销成功",null);
    }

}
