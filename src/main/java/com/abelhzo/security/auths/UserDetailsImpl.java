package com.abelhzo.security.auths;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.abelhzo.security.dtos.UserDTO;

/**
 * @author: Abel HZO
 * @project: springboot-security-basic
 * @file: UserDetailsImpl.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Lunes 03 Julio 2023, 23:23:27.
 * @description: El presente archivo UserDetailsImpl.java fue creado por Abel
 *               HZO.
 */
public class UserDetailsImpl implements UserDetails {

	private UserDTO userDTO;

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	private static final long serialVersionUID = 8381755905138575846L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<? extends GrantedAuthority> auths = this.userDTO.getUserRolsDTO().stream()
				.map(rol -> 
					new SimpleGrantedAuthority("ROLE_" + rol.getRolDTO().getRol()))
				.collect(Collectors.toList());

		return auths;
	}

	@Override
	public String getPassword() {
		return this.userDTO.getPassword();
	}

	@Override
	public String getUsername() {
		return this.userDTO.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
