package com.abelhzo.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abelhzo.security.dtos.ResponseWrapper;
import com.abelhzo.security.dtos.UserDTO;
import com.abelhzo.security.services.UserService;

/**
 * @author: Abel HZO
 * @project: springboot-security-basic
 * @file: UserController.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Viernes 30 Junio 2023, 17:43:06.
 * @description: El presente archivo UserController.java fue creado por Abel HZO.
 */
@RestController
@RequestMapping("/api/user")
public class UserController implements UserService {
	
	@Autowired
	private UserService userService;
	
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/id/{idUser}")
	public ResponseEntity<ResponseWrapper> findById(@PathVariable("idUser") Long idUser) {
		return userService.findById(idUser);
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/profile")
	public ResponseEntity<ResponseWrapper> findByUsername() {
		return userService.findByUsername();
	}

	@Override
	@Secured({ "ROLE_ADMIN" })
	@PostMapping("/save")
	public ResponseEntity<ResponseWrapper> saveUser(UserDTO userDTO) {
		return userService.saveUser(userDTO);
	}

	@Override
	@Secured({ "ROLE_ADMIN" })
	@PutMapping("/modify")
	public ResponseEntity<ResponseWrapper> modifyUser(UserDTO userDTO) {
		return userService.modifyUser(userDTO);
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/photo")
	public ResponseEntity<ResponseWrapper> modifyPhoto(UserDTO userDTO) {
		return userService.modifyPhoto(userDTO);
	}

	@Override
	@Secured({ "ROLE_ADMIN" })
	@PostMapping("/rol/add")
	public ResponseEntity<ResponseWrapper> addUserRol(UserDTO userDTO) {
		return userService.addUserRol(userDTO);
	}

	@Override
	@Secured({ "ROLE_ADMIN" })
	@PostMapping("/rol/remove")
	public ResponseEntity<ResponseWrapper> removeUserRol(UserDTO userDTO) {
		return userService.removeUserRol(userDTO);
	}
	
	@GetMapping("/change/pass")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<ResponseWrapper> changeAllPassword() {
		return userService.changeAllPassword();
	}

	@Override
	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/all")
	public ResponseEntity<ResponseWrapper> findAllUsers() {
		return userService.findAllUsers();
	}

}
