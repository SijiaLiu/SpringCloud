package com.lsj.settlement;

import com.lsj.settlement.data.SettlementTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * class_name: SettlementApplication
 * package: com.lsj.settlement
 * describe: Application
 * @author liusijia
 * @Date 2018/11/2
**/

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableAutoConfiguration
public class SettlementApplication {

	@Autowired
	private SettlementTicketRepository settlementTicketRepository;

	public static void main(String[] args) {
		SpringApplication.run(SettlementApplication.class, args);
	}

	@Value("${server.port}")
	String port;
	@RequestMapping("/hi")
	public String home(@RequestParam String name) {
		return "hi "+name+",i am from port:" +port;
	}
}
