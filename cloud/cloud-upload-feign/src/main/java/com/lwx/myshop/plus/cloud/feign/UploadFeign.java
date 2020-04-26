package com.lwx.myshop.plus.cloud.feign;

import com.lwx.myshop.plus.cloud.feign.fallback.UploadFeignFallback;
import com.lwx.myshop.plus.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 功能描述: 文件上传
 *
 * @author: 刘武祥
 * @Date: 2020/4/21 0021 11:10
 * @version v1.0.0
 * @see com.lwx.myshop.plus.cloud.feign
 */
@FeignClient(value = "cloud-upload", path = "upload", configuration = FeignRequestConfiguration.class,fallback = UploadFeignFallback.class)
public interface UploadFeign {

    /**
     * 功能描述: 文件上传
     * 
     * @param multipartFile {@link MultipartFile}
     * @return string @{@link String} 文件上传路径
     * @auther 刘武祥
     * @date 2020/4/21 0021 11:32
     */
    @PostMapping(value = "")
    String upload(@RequestPart(value = "multipartFile") MultipartFile multipartFile);

}
