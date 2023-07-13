package com.abelhzo.security.dtos;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author: Abel HZO
 * @project: springboot-security-basic
 * @file: ResponseWrapper.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Martes 04 Julio 2023, 14:17:03.
 * @description: El presente archivo ResponseWrapper.java fue creado por Abel
 *               HZO.
 */
public class ResponseWrapper {

	private String time;
	private String currentUser;
	private Collection<? extends GrantedAuthority> authorities;
	private TypeMessage typeMessage;
	private String message;
	private Object body;

	public ResponseWrapper() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		this.currentUser = userDetails.getUsername();
		this.authorities = userDetails.getAuthorities();
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCurrentUser() {
		return currentUser;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public TypeMessage getTypeMessage() {
		return typeMessage;
	}

	public void setTypeMessage(TypeMessage typeMessage) {
		this.typeMessage = typeMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

}
