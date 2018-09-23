package com.lxz.org.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration

@MapperScan(basePackages = {"com.lxz.org.dao"}, sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveConfig {
	
	
	@Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "datasource.slave")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
 
    @Bean(name = "slaveTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("slaveDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    
    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory basicSqlSessionFactory(@Qualifier("slaveDataSource") DataSource basicDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(basicDataSource);
        factoryBean.setTypeAliasesPackage("com.lxz.org.dao");
 
        return factoryBean.getObject();
    }


}
