package com.javateam.SpringBootSecurityMyBatisThymeleaf.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DBConfig {
	
	@Bean
    @ConfigurationProperties(prefix="spring.datasource")
    DataSourceProperties dataSourceProp() {
        return new DataSourceProperties();
    }
	
    @Primary
    @Bean(name="dataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    // @Qualifier("primary")
    DataSource dataSource() {
        return dataSourceProp().initializeDataSourceBuilder().build();
    }

    @Bean(name="hikariCP")
    // @Primary
    @ConfigurationProperties(prefix="spring.datasource.hikari") 
    DataSource hikariDataSource() {
        return DataSourceBuilder.create()
        			.type(HikariDataSource.class)
        			.build();
    }

    @Bean
	SqlSessionFactory sqlSessionFactory() throws Exception {
		
	    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	    factoryBean.setDataSource(hikariDataSource());
	    //factoryBean.setDataSource(dataSource());
	    factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
					.getResources("classpath:/mapper/*.xml"));
	    
	    factoryBean.addTypeAliases(com.javateam.SpringBootSecurityMyBatisThymeleaf.domain.Users.class);
	    factoryBean.addTypeAliases(com.javateam.SpringBootSecurityMyBatisThymeleaf.domain.Role.class);
	    factoryBean.addTypeAliases(com.javateam.SpringBootSecurityMyBatisThymeleaf.domain.CustomUser.class);
	    
	    org.apache.ibatis.session.Configuration configuration 
	    	= new org.apache.ibatis.session.Configuration();
	    
	    configuration.setMapUnderscoreToCamelCase(true);	    
	    factoryBean.setConfiguration(configuration);
	    
	    return factoryBean.getObject();
	}
	
	@Bean(name="sqlSession")	
	SqlSessionTemplate sqlSessionTemplate() throws Exception {
		
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	@Bean
    PlatformTransactionManager getTransactionManager() {
	        
		return new DataSourceTransactionManager(this.hikariDataSource());
	}
	
//	@Bean
//	DataSourceTransactionManager getTransactionManager2() {
//		
//		return new DataSourceTransactionManager(this.hikariDataSource());
//	} //
	
}
