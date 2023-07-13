package com.abelhzo.security.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author: Abel HZO
 * @project: springboot-security-basic
 * @file: HandleErrorsController.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Martes 04 Julio 2023, 16:57:01.
 * @description: El presente archivo HandleErrorsController.java fue creado por Abel HZO.
 */
@Controller
public class HandleErrorsController implements ErrorController {
	
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		if(status != null) {
		
			Integer statusCode = Integer.valueOf(status.toString());
			System.out.println("CODIGO: " + statusCode);
	        if(statusCode == HttpStatus.FORBIDDEN.value()) {
	        	return "error/403";
	        }
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	            return "error/404";
	        }
	        if(statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
	            return "error/405";
	        }
	        if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            return "error/500";
	        }
		}
		
		return "error";
	}

}
