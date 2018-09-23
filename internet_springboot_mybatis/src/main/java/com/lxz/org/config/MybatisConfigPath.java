package com.lxz.org.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(MybatisConfig.class)
public class MybatisConfigPath {
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer=new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionTemplateBeanName("sqlSessionTemplate");
		mapperScannerConfigurer.setBasePackage("com.lxz.org.dao");
		return mapperScannerConfigurer;
	}
}
