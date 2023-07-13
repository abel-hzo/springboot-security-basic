package com.abelhzo.security.dtos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author: Abel HZO
 * @project: springboot-security-basic
 * @file: UserDTO.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Viernes 30 Junio 2023, 16:04:18.
 * @description: El presente archivo UserDTO.java fue creado por Abel HZO.
 */
public class UserDTO {

	private Long idUser;
	private String username;
	private String password;
	private Date birthday;
	private byte[] photo;
	private String creationDate;
	private List<UserRolDTO> userRolsDTO = new ArrayList<>(0); // The elements of a Set cannot be accessed by index
	@JsonIgnore
	private MultipartFile file;

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public List<UserRolDTO> getUserRolsDTO() {
		return userRolsDTO;
	}

	public void setUserRolsDTO(List<UserRolDTO> userRolsDTO) {
		this.userRolsDTO = userRolsDTO;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
