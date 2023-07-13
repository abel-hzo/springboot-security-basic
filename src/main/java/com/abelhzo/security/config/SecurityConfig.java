package com.abelhzo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.abelhzo.security.auths.SuccessHandler;

/**
 * @author: Abel HZO
 * @project: springboot-security-basic
 * @file: SecurityConfig.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Lunes 03 Julio 2023, 23:38:21.
 * @description: El presente archivo SecurityConfig.java fue creado por Abel HZO.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true,
					  securedEnabled = true,
					  jsr250Enabled = true)
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
		return PasswordEncoderFactories.createDelegatingPasswordEncoder(); // 	{noop}contrasenia
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
			
			.authorizeHttpRequests((authz) -> authz
					.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
					.requestMatchers("/profile").authenticated()
					.requestMatchers("/createuser").hasAnyRole("ADMIN")
					.requestMatchers("/listusers").hasRole("ADMIN")
					.requestMatchers("/error/**").authenticated()
					.requestMatchers("/api/user/**").authenticated()
					//css and javascript
					.requestMatchers("/admin/**").hasAnyRole("ADMIN", "USER")
					.requestMatchers("/user/**").hasRole("USER")
					.requestMatchers("/authenticated/**").authenticated()
					.requestMatchers("/fontawesome/**", "/public/**").permitAll())
			
			.headers((header) -> header
					.frameOptions().sameOrigin())
			
			//.httpBasic() Es para hacer Login atravéz de un cuadro de dialogo
			//es muy útil para hacer pruebas de login. De igual manera sirve para 
			//hacer pruebas desde postman.
//			.httpBasic().and()
			
			.formLogin((login) -> login
					.loginPage("/mylogin").permitAll()
					.loginProcessingUrl("/loginUrl")
					.successHandler(new SuccessHandler()))  
			
			.logout()
			.and()
			.csrf().disable();
		
		return http.build();
		
	}

}
