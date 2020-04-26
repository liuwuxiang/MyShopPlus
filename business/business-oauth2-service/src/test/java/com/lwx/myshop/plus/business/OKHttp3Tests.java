package com.lwx.myshop.plus.business;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonMapFormatVisitor;
import com.google.common.collect.Maps;
import com.lwx.myshop.plus.commons.utils.MapperUtils;
import com.lwx.myshop.plus.commons.utils.OkHttpClientUtil;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author: 刘武祥
 * @Date: 2020/4/16 0016 11:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OKHttp3Tests {

    @Test
    public void testGet() {
        //1.定义一个地址
        String url = "https://www.baidu.com";
        //2.实例化网络请求客户端
        OkHttpClient client = new OkHttpClient();
        //发起一个请求
        Request request = new Request.Builder()
                .url(url)
                .build();
        //得到请求结果
        Call call = client.newCall(request);
        try {
            //响应结果
            Response response = call.execute();
            //得到响应体
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPost() {
        String url = "http://localhost:9001/oauth/token";
        //创建客户端
        OkHttpClient client = new OkHttpClient();
        //封装参数
        RequestBody body = new FormBody.Builder()
                .add("username", "admin")
                .add("password", "123456")
                .add("grant_type", "password")
                .add("client_id", "client")
                .add("client_secret", "secret")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUtilsPost(){
        String url = "http://localhost:9001/oauth/token";
        Map<String,String> params = Maps.newHashMap();
        params.put("username","admin");
        params.put("password","123456");
        //密码模式
        params.put("grant_type","password");
        params.put("client_id","client");
        params.put("client_secret","secret");

        try {
            //单例模式
            Response response = OkHttpClientUtil.getInstance().postData(url, params);
            //json数据
            String jsonString = response.body().string();
            //json转mapper
            Map<String, Object> jsonMap = MapperUtils.json2map(jsonString);
            String token = String.valueOf(jsonMap.get("access_token"));
            System.out.println(token);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
