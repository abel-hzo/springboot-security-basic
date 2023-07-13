package com.abelhzo.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: Abel HZO
 * @project: springboot-security-basic
 * @file: WebMvcConfig.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Lunes 03 Julio 2023, 23:10:50.
 * @description: El presente archivo WebMvcConfig.java fue creado por Abel HZO.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/mylogin").setViewName("custom_login");
		registry.addViewController("/profile").setViewName("perfil");
		registry.addViewController("/createuser").setViewName("create_user");
		registry.addViewController("/listusers").setViewName("list_users");
		WebMvcConfigurer.super.addViewControllers(registry);
	}

//	@Override
//	public void configureViewResolvers(ViewResolverRegistry registry) {
//		registry.jsp("/WEB-INF/pages/", ".jsp");
//		WebMvcConfigurer.super.configureViewResolvers(registry);
//	}

//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
//		WebMvcConfigurer.super.addResourceHandlers(registry);
//	}

}
