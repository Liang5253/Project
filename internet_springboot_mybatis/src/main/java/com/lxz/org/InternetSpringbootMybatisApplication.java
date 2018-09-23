package com.lxz.org;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@MapperScan("com.lxz.org.dao")
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,MybatisAutoConfiguration.class})
public class InternetSpringbootMybatisApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(InternetSpringbootMybatisApplication.class, args);
		
		
	
	
	}
}
