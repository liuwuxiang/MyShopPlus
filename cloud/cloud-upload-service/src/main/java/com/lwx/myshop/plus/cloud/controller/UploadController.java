package com.lwx.myshop.plus.cloud.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.lwx.myshop.plus.cloud.dto.FileInfo;
import com.lwx.myshop.plus.commons.dto.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 功能描述: 文件上传服务
 *
 * @author: 刘武祥
 * @Date: 2020/4/21 0021 11:48
 * @version 1.0.0
 * @see com.lwx.myshop.plus.cloud.controller
 */
@RestController
@RequestMapping(value = "upload")
public class UploadController {

    private static final String ENDPOINT = "oss-cn-shenzhen.aliyuncs.com";
    private static final String ACCESS_KEY_ID = "LTAIUEM4x1YOqT0O";
    private static final String ACCESS_KEY_SECRET = "XVRECYNWqS7uzssIXeNrcgKIamBjTh";
    private static final String BUCKET_NAME = "javasite";

    /**
     * 功能描述: 文件上传
     *
     * @param multipartFile {@code MultipartFile}
     * @return  {@link ResponseResult<FileInfo>} 文件上传证书
     * @auther 刘武祥
     * @date 2020/4/21 0021 14:07
     */
    @PostMapping(value = "")
    public ResponseResult<FileInfo> upload(MultipartFile multipartFile) {
        //1.获取到 文件名 文件名后缀
        String fileName = multipartFile.getOriginalFilename();
        assert fileName != null;
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        //2.生成一个唯一的随机的新文件名称
        String newName = UUID.randomUUID() + "." + suffix;

        //3.创建OSS对象存储服务客户端实例
        OSS client = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

        try {
            //4.以字节形式上传
            client.putObject(new PutObjectRequest(BUCKET_NAME, newName, new ByteArrayInputStream(multipartFile.getBytes())));
            //5.上传文件路径 = http://BUCKET_NAME.ENDPOINT/自定义路径/fileName
            return new ResponseResult<FileInfo>(ResponseResult.CodeStatus.OK, "文件上传成功", new FileInfo("http://" + BUCKET_NAME + "." + ENDPOINT + "/" + newName));
        } catch (IOException e) {
            return new ResponseResult<FileInfo>(ResponseResult.CodeStatus.FAIL, "文件上传失败，请重试");
        } finally {
            //6.关闭客户端
            client.shutdown();
        }
    }

}
