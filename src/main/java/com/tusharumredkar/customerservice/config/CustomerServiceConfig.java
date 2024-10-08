package com.tusharumredkar.customerservice.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class CustomerServiceConfig {

	@Autowired
	private Environment env;

	@Bean
	public DataSource getDataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(env.getProperty("spring.datasource.driverClassName"));
		dataSourceBuilder.url(env.getProperty("spring.datasource.url"));
		dataSourceBuilder.username(env.getProperty("spring.datasource.username"));
		dataSourceBuilder.password(env.getProperty("spring.datasource.password"));
		return dataSourceBuilder.build();
	}
}
