package cn.hhp.middleware.dynamic.thread.pool.sdk.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// 动态线程池服务
@Configuration
public class DynamicThreadPoolAutoConfig {

    @Bean("dynamicThreadPollService")
    public void dynamicThreadPollService(ApplicationContext applicationContext){


    }
}
