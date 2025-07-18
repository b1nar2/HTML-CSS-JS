package com.javateam.SpringBootMyBatisThymeleaf.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DbConfig {

    @Bean(name="dataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.hikari") 
    DataSource hikariDataSource() {
    
    	return DataSourceBuilder.create()
        			.type(HikariDataSource.class)
        			.build();
    }
	
	@Bean(name="hikariCP")
	SqlSessionFactory sqlSessionFactory() throws Exception {
		
	    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	    factoryBean.setDataSource(hikariDataSource());
	    
	    return factoryBean.getObject();
	}
	
	@Bean(name="sqlSession")	
	SqlSessionTemplate sqlSessionTemplate() throws Exception {
		
		return new SqlSessionTemplate(sqlSessionFactory());
	}

}
