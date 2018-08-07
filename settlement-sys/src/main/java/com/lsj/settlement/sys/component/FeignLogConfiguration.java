package com.lsj.settlement.sys.component;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class_name: FeginLogConfiguration
 * package: com.lsj.settlement.sys.component
 * describe: 打印 FeginClient 日志
 * @author liusijia
 * @Date 2018/7/31
**/

@Configuration
public class FeignLogConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }

}
