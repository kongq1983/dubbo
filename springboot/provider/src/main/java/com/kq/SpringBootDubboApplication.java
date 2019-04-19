package com.kq;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 在springboot中通过注解的方式启动dubbo服务
 * SpringBootDubboApplication
 */
@SpringBootApplication
public class SpringBootDubboApplication {
	
	/**
	 * spring-boot方式启动服务
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("dubbo.application.logger","slf4j");
		SpringApplication.run(SpringBootDubboApplication.class, args);
	}
}
