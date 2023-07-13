package com.abelhzo.security.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: Abel HZO
 * @project: springboot-security-basic
 * @file: App.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Viernes 30 Junio 2023, 15:12:33.
 * @description: El presente archivo App.java fue creado por Abel HZO.
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.abelhzo.security" })
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
