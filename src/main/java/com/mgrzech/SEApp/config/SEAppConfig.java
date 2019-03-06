package com.mgrzech.SEApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@ComponentScan
@PropertySource("classpath:application.properties")
@EnableJpaRepositories
public class SEAppConfig {

	@Autowired
	private Environment env;
	
	@Bean
	public DriverManagerDataSource dataSource() {
	    
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();   
	    driverManagerDataSource.setUrl(env.getProperty("spring.datasource.url"));
	    driverManagerDataSource.setUsername(env.getProperty("spring.datasource.username"));
	    driverManagerDataSource.setPassword(env.getProperty("spring.datasource.password"));   
	    return driverManagerDataSource;
	    
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();		
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan( new String[] { "com.mgrzech.SEApp.config.entity" });
		return sessionFactory;
		
	}
	
	
}
