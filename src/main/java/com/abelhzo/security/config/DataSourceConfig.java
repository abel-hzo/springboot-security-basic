package com.abelhzo.security.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author: Abel HZO
 * @project: springboot-security-basic
 * @file: DataSourceConfig.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Viernes 30 Junio 2023, 15:11:15.
 * @description: El presente archivo DataSourceConfig.java fue creado por Abel
 *               HZO.
 */
@Configuration
@EntityScan(basePackages = { "com.abelhzo.security.entities" })
@EnableJpaRepositories(basePackages = { "com.abelhzo.security.repositories" })
public class DataSourceConfig {

	private final String PATH_DATABASE = new FileSystemResource("")
			.getFile()
			.getAbsolutePath()
			.concat("/src/main/resources/securedb");

	@Bean
	public DataSource dataSourceH2() {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.h2.Driver");
		dataSourceBuilder.url("jdbc:h2:" + PATH_DATABASE);
		dataSourceBuilder.username("abelhzo");
		dataSourceBuilder.password("54321");
		return dataSourceBuilder.build();
	}

}
