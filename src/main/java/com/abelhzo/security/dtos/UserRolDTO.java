package com.abelhzo.security.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author: Abel HZO
 * @project: springboot-security-basic
 * @file: UserRolDTO.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Viernes 30 Junio 2023, 16:05:01.
 * @description: El presente archivo UserRolDTO.java fue creado por Abel HZO.
 */
public class UserRolDTO {

	private Long idUserRol;
	@JsonIgnore
	private UserDTO userDTO;
	private RolDTO rolDTO;
	private String creationDate;

	public Long getIdUserRol() {
		return idUserRol;
	}

	public void setIdUserRol(Long idUserRol) {
		this.idUserRol = idUserRol;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public RolDTO getRolDTO() {
		return rolDTO;
	}

	public void setRolDTO(RolDTO rolDTO) {
		this.rolDTO = rolDTO;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

}
