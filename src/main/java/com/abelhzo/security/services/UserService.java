package com.abelhzo.security.services;

import org.springframework.http.ResponseEntity;

import com.abelhzo.security.dtos.ResponseWrapper;
import com.abelhzo.security.dtos.UserDTO;

/**
 * @author: Abel HZO
 * @project: springboot-security-basic
 * @file: UserService.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Viernes 30 Junio 2023, 16:30:27.
 * @description: El presente archivo UserService.java fue creado por Abel HZO.
 */
public interface UserService {
	
	ResponseEntity<ResponseWrapper> findById(Long idUser);

	ResponseEntity<ResponseWrapper> findByUsername();
		
	ResponseEntity<ResponseWrapper> saveUser(UserDTO userDTO);
	
	ResponseEntity<ResponseWrapper> modifyUser(UserDTO userDTO);
	
	ResponseEntity<ResponseWrapper> modifyPhoto(UserDTO userDTO);
	
	ResponseEntity<ResponseWrapper> addUserRol(UserDTO userDTO);
	
	ResponseEntity<ResponseWrapper> removeUserRol(UserDTO userDTO);

	ResponseEntity<ResponseWrapper> changeAllPassword();
	
	ResponseEntity<ResponseWrapper> findAllUsers();
	
}
