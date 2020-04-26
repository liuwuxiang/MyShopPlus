package com.lwx.myshop.plus.configuration;

import org.springframework.context.annotation.Configuration;
import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
/**
 * 功能描述: Dubbo Sentinel AOP
 * <p>
 * Description: 仅 Dubbo 服务需要该配置，Spring Cloud Alibaba 无需加载该配置
 * </p>
 * @author: 刘武祥
 * @Date: 2020/4/22 0022 16:18
 * @version v1.0.0
 * @see com.lwx.myshop.plus.configuration
 */
@Configuration
public class DubboSentinelConfiguration {

    /**
     * 功能描述: 使用 Spring AOP 的方式显示的注册 SentinelResourceAspect 为一个 Bean
     *
     * @return com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect {@link SentinelResourceAspect}
     * @auther 刘武祥
     * @date 2020/4/22 0022 16:39
     */
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

}
