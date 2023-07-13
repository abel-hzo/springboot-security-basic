package com.abelhzo.security.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.abelhzo.security.dtos.ResponseWrapper;
import com.abelhzo.security.dtos.TypeMessage;
import com.abelhzo.security.utils.Utils;

/**
 * @author: Abel HZO
 * @project: springboot-security-basic
 * @file: HandlerExceptions.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Martes 04 Julio 2023, 16:39:50.
 * @description: El presente archivo HandlerExceptions.java fue creado por Abel HZO.
 */
@RestControllerAdvice
public class HandlerExceptions {
	
	@Autowired
	private Utils utils;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<ResponseWrapper> name(MethodArgumentNotValidException ex) {
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		Map<String, String> map = new HashMap<>();
		errors.forEach(error -> {
			map.put(error.getField(), error.getDefaultMessage());
		});
		
		return utils.buildResponseEntity(TypeMessage.BAD_REQUEST, map); 
	}

}
