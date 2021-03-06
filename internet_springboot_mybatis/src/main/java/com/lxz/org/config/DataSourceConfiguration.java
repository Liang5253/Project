package com.lxz.org.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:application.properties")
public class DataSourceConfiguration {
	
	@Value("${spring.datasource.driverClassName}")
	private String driver;
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
//	
//	@Value("${spring.datasource.max-active}")
//	private int maxActive;
//	
//	@Value("${spring.datasource.max-idle}")
//	private int maxIdel;
//	
//	@Value("${spring.datasource.max-wait}")
//	private long maxWait;
//	
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
//        dataSource.setMaxActive(maxActive);
//        dataSource.setMaxIdle(maxIdel);
//        dataSource.setMaxWait(maxWait);
//        dataSource.setValidationQuery("SELECT 1");
//        dataSource.setTestOnBorrow(true);
        return dataSource;
	}
	
}
