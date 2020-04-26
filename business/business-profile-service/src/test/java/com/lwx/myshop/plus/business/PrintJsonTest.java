package com.lwx.myshop.plus.business;

import com.lwx.myshop.plus.business.dto.params.ProfileParam;
import com.lwx.myshop.plus.commons.utils.MapperUtils;
import org.junit.Test;

/**
 * 功能描述:
 *
 * @author: 刘武祥
 * @Date: 2020/4/20 0020 17:27
 */
public class PrintJsonTest {

    @Test
    public void testPrintJson() throws Exception {
        System.out.println(MapperUtils.obj2json(new ProfileParam()));
    }

}
