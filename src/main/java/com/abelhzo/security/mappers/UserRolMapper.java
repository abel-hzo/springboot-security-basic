package com.abelhzo.security.mappers;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.abelhzo.security.dtos.RolDTO;
import com.abelhzo.security.dtos.UserDTO;
import com.abelhzo.security.dtos.UserRolDTO;
import com.abelhzo.security.entities.Rol;
import com.abelhzo.security.entities.User;
import com.abelhzo.security.entities.UserRol;

/**
 * @author: Abel HZO
 * @project: springboot-security-basic
 * @file: UserRolMapper.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Viernes 30 Junio 2023, 16:12:47.
 * @description: El presente archivo UserRolMapper.java fue creado por Abel HZO.
 */
@Mapper(componentModel = ComponentModel.SPRING)
public interface UserRolMapper {
	
	@Named(value = "customUserRolDTO")
	@Mappings({
		@Mapping(target = "rolDTO", source = "rol"),
		@Mapping(target = "userDTO", ignore = true),
		@Mapping(target = "creationDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
	})
	UserRolDTO toUserRolDTO(UserRol userRol);
	
	@Named(value = "customUserRol")
	@Mappings({
		@Mapping(target = "rol", source = "rolDTO", qualifiedByName = "customRol"),
		@Mapping(target = "user", source = "userDTO", qualifiedByName = "customUser"),
		@Mapping(target = "creationDate", qualifiedByName = "stringToTimestamp")
	})
	UserRol toUserRol(UserRolDTO userRolDTO);
	
	@Named(value = "customRol")
	@Mappings({
		@Mapping(target = "rol", ignore = true)
	})
	Rol toRol(RolDTO rolDTO);
	
	@Named(value = "customUser")
	@Mappings({
		@Mapping(target = "username", ignore = true),
		@Mapping(target = "password", ignore = true),
		@Mapping(target = "photo", ignore = true),
		@Mapping(target = "birthday", ignore = true),
		@Mapping(target = "creationDate", ignore = true)
	})
	User toUser(UserDTO userDTO);
	
	@Named("stringToTimestamp")
	default Timestamp map(String value) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTimestamp = value.formatted(formatter);
		return Timestamp.valueOf(formattedTimestamp);
	}

}
