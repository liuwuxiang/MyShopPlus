package com.lwx.myshop.plus.cloud.feign.fallback;

import com.lwx.myshop.plus.cloud.feign.UploadFeign;
import com.lwx.myshop.plus.commons.dto.ResponseResult;
import com.lwx.myshop.plus.commons.utils.MapperUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传熔断器
 * <p>
 * Description:
 * </p>
 *
 * @author 刘武祥
 * @version v1.0.0
 * @date 2020/4/23 0023 11:50
 * @see com.lwx.myshop.plus.cloud.feign.fallback
 */
@Component
public class UploadFeignFallback implements UploadFeign {

    private static final String BREAKING_MESSAGE = "请求失败了，请检查您的网络";

    /**
     * 功能描述: 文件上传
     *
     * @param multipartFile {@link MultipartFile}
     * @return java.lang.String {@code String}
     * @auther 刘武祥
     * @date 2020/4/23 0023 11:50
     */
    @Override
    public String upload(MultipartFile multipartFile) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseResult.CodeStatus.BREAKING, BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
