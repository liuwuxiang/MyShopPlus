package com.lwx.myshop.plus.business.feign.fallback;

import com.lwx.myshop.plus.business.dto.UmsAdminDTO;
import com.lwx.myshop.plus.business.dto.params.IconParam;
import com.lwx.myshop.plus.business.dto.params.PasswordParam;
import com.lwx.myshop.plus.business.dto.params.ProfileParam;
import com.lwx.myshop.plus.business.feign.ProfileFeign;
import com.lwx.myshop.plus.commons.dto.ResponseResult;
import com.lwx.myshop.plus.commons.utils.MapperUtils;
import org.springframework.stereotype.Component;

/**
 * 功能描述: 个人信息服务熔断器
 *
 * @author: 刘武祥
 * @Date: 2020/4/22 0022 14:08
 * @see com.lwx.myshop.plus.business.feign.ProfileFeign
 * @version v1.0.0
 */
@Component
public class ProfileFeignFallback implements ProfileFeign {

    public static final String BREAKING_MESSAGE = "您的网络有问题，请检查";

    @Override
    public String info(String username) {
        UmsAdminDTO dto = new UmsAdminDTO();
        dto.setEmail("494775947@qq.com");
        try {
            return MapperUtils.obj2json(new ResponseResult<UmsAdminDTO>(ResponseResult.CodeStatus.BREAKING,BREAKING_MESSAGE,dto));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String update(ProfileParam profileParam) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseResult.CodeStatus.BREAKING,BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String modifyPassword(PasswordParam passwordParam) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseResult.CodeStatus.BREAKING,BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String modifyIcon(IconParam iconParam) {
        try {
            return MapperUtils.obj2json(new ResponseResult<Void>(ResponseResult.CodeStatus.BREAKING,BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
