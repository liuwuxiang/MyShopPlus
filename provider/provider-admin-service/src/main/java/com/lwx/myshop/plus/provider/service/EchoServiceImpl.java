package com.lwx.myshop.plus.provider.service;

import com.lwx.myshop.plus.provider.api.EchoService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author Administrator
 */
@Service(version = "1.0.0")
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String string) {
        return "Dubbo " + string;
    }
}
