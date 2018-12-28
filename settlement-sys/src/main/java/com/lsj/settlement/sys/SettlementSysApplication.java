package com.lsj.settlement.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * class_name: SettlementSysApplication
 * package: com.lsj.settlement.sys
 * describe: TODO
 * @author liusijia
 * @Date 2018/11/2
**/

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan({"com.lsj.settlement.sys"})
public class SettlementSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(SettlementSysApplication.class, args);
	}
}
