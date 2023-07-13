package com.abelhzo.security.services.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.abelhzo.security.dtos.ResponseWrapper;
import com.abelhzo.security.dtos.RolDTO;
import com.abelhzo.security.dtos.TypeMessage;
import com.abelhzo.security.dtos.UserDTO;
import com.abelhzo.security.dtos.UserRolDTO;
import com.abelhzo.security.entities.User;
import com.abelhzo.security.entities.UserRol;
import com.abelhzo.security.mappers.UserMapper;
import com.abelhzo.security.mappers.UserRolMapper;
import com.abelhzo.security.repositories.UserRepository;
import com.abelhzo.security.repositories.UserRolRepository;
import com.abelhzo.security.services.UserService;
import com.abelhzo.security.utils.Utils;

import jakarta.persistence.EntityNotFoundException;

/**
 * @author: Abel HZO
 * @project: springboot-security-basic
 * @file: UserServiceImpl.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Viernes 30 Junio 2023, 16:40:14.
 * @description: El presente archivo UserServiceImpl.java fue creado por Abel
 *               HZO.
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRolRepository userRolRepository;

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRolMapper userRolMapper;
	
	@Autowired
	private Utils util;
	
	@Override
	public ResponseEntity<ResponseWrapper> findById(Long idUser) {
		
		Optional<User> findById = userRepository.findById(idUser);
		
		ResponseEntity<ResponseWrapper> buildResponseEntity = null;
		
		if(findById.isPresent()) {
			buildResponseEntity = util.buildResponseEntity(TypeMessage.SUCCESS, userMapper.toUserDTO(findById.get()));
		} else {
			buildResponseEntity = util.buildResponseEntity(TypeMessage.NOT_FOUND, "El usuario no existe.");
		}
		
		return buildResponseEntity;
	}

	@Override
	public ResponseEntity<ResponseWrapper> findByUsername() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		Optional<User> findByUsername = userRepository.findByUsername(userDetails.getUsername());

		ResponseEntity<ResponseWrapper> responseEntity = null;

		if (findByUsername.isPresent()) {
			responseEntity = util.buildResponseEntity(TypeMessage.SUCCESS, userMapper.toUserDTO(findByUsername.get()));
		} else {
			responseEntity = util.buildResponseEntity(TypeMessage.INVALID_LOGIN, "No se encontro el usuario.");
		}

		return responseEntity;
	}

	@Override
	public ResponseEntity<ResponseWrapper> saveUser(UserDTO userDTO) {

		userDTO.setIdUser(System.currentTimeMillis());
		userDTO.setPassword("{noop}"+userDTO.getPassword());
		userDTO.setPhoto(util.loadDefaultImageUser());
		userDTO.setCreationDate(new Timestamp(System.currentTimeMillis()).toString());

			UserRolDTO userRolDTO = new UserRolDTO();
				UserDTO myUserDTO = new UserDTO();
				myUserDTO.setIdUser(userDTO.getIdUser());
				RolDTO rolDTO = new RolDTO();
				rolDTO.setIdRol(1);
			userRolDTO.setUserDTO(myUserDTO);
			userRolDTO.setRolDTO(rolDTO);
			userRolDTO.setCreationDate(userDTO.getCreationDate());

		userDTO.getUserRolsDTO().add(userRolDTO);

		User user = userMapper.toUser(userDTO);
		
		User save = userRepository.save(user);

		ResponseEntity<ResponseWrapper> responseEntity = null;
		if (save != null) {
			responseEntity = util.buildResponseEntity(TypeMessage.SUCCESS, userMapper.toUserDTO(save));
		} else {
			responseEntity = util.buildResponseEntity(TypeMessage.FAILURE, null);
		}

		return responseEntity;
	}

	@Override
	public ResponseEntity<ResponseWrapper> modifyUser(UserDTO userDTO) {

		ResponseEntity<ResponseWrapper> responseEntity = null;
		try {
			User user = userRepository.getReferenceById(userDTO.getIdUser());
			userRepository.modifyUser(userDTO.getUsername(), userDTO.getPassword(), userDTO.getBirthday(),
					userDTO.getIdUser());
			responseEntity = util.buildResponseEntity(TypeMessage.SUCCESS, userMapper.toUserDTO(user));
		} catch(EntityNotFoundException enfe) {
			responseEntity = util.buildResponseEntity(TypeMessage.FAILURE, "El usuario no existe.");
		}

		return responseEntity;
	}

	@Override
	public ResponseEntity<ResponseWrapper> modifyPhoto(UserDTO userDTO) {

		ResponseEntity<ResponseWrapper> responseEntity = null;
		try {
			User user = userRepository.getReferenceById(userDTO.getIdUser());
			userRepository.modifyPhoto(userDTO.getFile().getBytes(), userDTO.getIdUser());
			responseEntity = util.buildResponseEntity(TypeMessage.SUCCESS, userMapper.toUserDTO(user));
		} catch(EntityNotFoundException enfe) {
			responseEntity = util.buildResponseEntity(TypeMessage.FAILURE, "El usuario no existe.");
		} catch (IOException e) {
			responseEntity = util.buildResponseEntity(TypeMessage.FAILURE, "Error al guardar el archivo.");
		}

		return responseEntity;
	}

	@Override
	public ResponseEntity<ResponseWrapper> addUserRol(UserDTO userDTO) {
		
		List<UserRolDTO> list = userDTO.getUserRolsDTO();
		UserRol userRol = userRolRepository.existsUserRol(list.get(0).getUserDTO().getIdUser(), list.get(0).getRolDTO().getIdRol());
		
		ResponseEntity<ResponseWrapper> responseEntity = null;
		if(userRol == null) {
			userRolRepository.addUserRol(list.get(0).getUserDTO().getIdUser(), list.get(0).getRolDTO().getIdRol(), new Timestamp(System.currentTimeMillis()));
			responseEntity = util.buildResponseEntity(TypeMessage.SUCCESS, list.get(0).getUserDTO().getIdUser() + ", "+ list.get(0).getRolDTO().getIdRol());
		} else {
			responseEntity = util.buildResponseEntity(TypeMessage.DUPLICATED, userRolMapper.toUserRolDTO(userRol));
		}
		
		return responseEntity;
	}

	@Override
	public ResponseEntity<ResponseWrapper> removeUserRol(UserDTO userDTO) {
		
		List<UserRolDTO> list = userDTO.getUserRolsDTO();
		UserRol userRol = userRolRepository.existsUserRol(list.get(0).getUserDTO().getIdUser(), list.get(0).getRolDTO().getIdRol());
		
		ResponseEntity<ResponseWrapper> responseEntity = null;
		
		if(userRol != null) {
			userRolRepository.removeUserRol(list.get(0).getUserDTO().getIdUser(), list.get(0).getRolDTO().getIdRol());
			responseEntity = util.buildResponseEntity(TypeMessage.SUCCESS, userRolMapper.toUserRolDTO(userRol));
		} else {
			responseEntity = util.buildResponseEntity(TypeMessage.FAILURE, "El dato no existe.");
		}
		
		return responseEntity;
	}

	@Override
	public ResponseEntity<ResponseWrapper> changeAllPassword() {
		
		List<User> findAll = userRepository.findAll();
		
		findAll.forEach(user -> {
			if(!user.getPassword().contains("{noop}"))
				userRepository.changeAllPassword("{noop}"+user.getPassword(), user.getIdUser());
		});
		
		return util.buildResponseEntity(TypeMessage.SUCCESS, "Passwords Modificados.");
	}

	@Override
	public ResponseEntity<ResponseWrapper> findAllUsers() {
		
		List<User> findAll = userRepository.findAll();
		
		ResponseEntity<ResponseWrapper> buildResponseEntity = util.buildResponseEntity(TypeMessage.SUCCESS, userMapper.toListUserDTO(findAll));
		
		return buildResponseEntity;
	}

}
