package com.abelhzo.security.auths;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abelhzo.security.dtos.UserDTO;
import com.abelhzo.security.entities.User;
import com.abelhzo.security.mappers.UserMapper;
import com.abelhzo.security.repositories.UserRepository;

/**
 * @author: Abel HZO
 * @project: springboot-security-basic
 * @file: UserDetailsServiceImpl.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Lunes 03 Julio 2023, 23:19:39.
 * @description: El presente archivo UserDetailsServiceImpl.java fue creado por Abel HZO.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> userOpt = userRepository.findByUsername(username);
		
		UserDTO userDTO = null;
		if(userOpt.isPresent()) {
			userDTO = userMapper.toUserDTO(userOpt.get());
		} 
		
		UserDetailsImpl userDetails = new UserDetailsImpl();
		
		if(userDTO != null) 
			userDetails.setUserDTO(userDTO);
		else 
			throw new UsernameNotFoundException("Usuario no encontrado.");
		
		return userDetails;
	}

}
