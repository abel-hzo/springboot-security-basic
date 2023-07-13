package com.abelhzo.security.auths;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author: Abel HZO
 * @project: springboot-security-basic
 * @file: SuccessHandler.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Martes 04 Julio 2023, 20:53:21.
 * @description: El presente archivo SuccessHandler.java fue creado por Abel HZO.
 */
public class SuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String contextPath = request.getContextPath();
		
		AtomicReference<String> value = new AtomicReference<>();
		
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		value.set(contextPath + "/profile");
		
		response.sendRedirect(value.get());
		
	}

}
