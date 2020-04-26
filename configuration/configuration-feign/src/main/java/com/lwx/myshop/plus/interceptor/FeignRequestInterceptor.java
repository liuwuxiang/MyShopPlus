package com.lwx.myshop.plus.interceptor;

import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Enumeration;

/**
 * 功能描述: feign请求拦截器
 *
 * <p>
 * Description: 用于设置请求头，传递 Token
 * </p>
 *
 * @author: 刘武祥
 * @version v1.0.0
 * @Date: 2020/4/17 0017 16:45
 * @see com.lwx.myshop.plus.interceptor
 */
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //1.请求上下文中 获取请求属性
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //2.获取请求  断言不等于null
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        //3.设置请求头 获取所有请求头名称
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            //3.1.遍历取出 请求头名称中的每一个元素
            while (headerNames.hasMoreElements()) {
                //3.2.拿到请求头的 name 通过name 拿到 value
                String name = headerNames.nextElement();
                String value = request.getHeader(name);
                //3.3.将请求头设置进feign
                requestTemplate.header(name,value);
            }
        }

        //4.设置请求体,主要是为了传递参数 access_token
        Enumeration<String> parameterNames = request.getParameterNames();
        StringBuilder body = new StringBuilder();
        if (parameterNames != null) {
            // 遍历取出 每一个参数
            while (parameterNames.hasMoreElements()) {
                //取出name和value
                String name = parameterNames.nextElement();
                String value = request.getParameter(name);

                //将令牌放入请求头 header  Bearer表示 空格
                if ("access_token".equals(name)){
                    requestTemplate.header("authorization", "Bearer " + value);
                }

                //其他参数封装成请求体
                else {
                    body.append(name).append("=").append(value).append("&");
                }

            }
        }

        // 5.设置请求体
        if (body.length() > 0 ){
            //去掉最后一位 & 符号
            body.deleteCharAt(body.length() - 1);
            requestTemplate.body(Request.Body.bodyTemplate(body.toString(), Charset.defaultCharset()));
        }

    }
}
