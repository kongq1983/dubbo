package com.kq.springboot;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.kq.api.IDemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring-boot结合dubbo示例
 * SpringBootDubboConsumerApplication
 */
@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.kq")
public class SpringBootDubboConsumerApplication {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Reference(version = "${demo.service.version}")
	private IDemoService demoService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDubboConsumerApplication.class, args);
	}


//	@Bean
//	public ApplicationRunner runner() {
//		return args -> logger.info(demoService.getMessage("dubbo"));
//	}
}
