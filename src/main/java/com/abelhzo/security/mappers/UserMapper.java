package com.abelhzo.security.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.Mappings;

import com.abelhzo.security.dtos.UserDTO;
import com.abelhzo.security.entities.User;

/**
 * @author: Abel HZO
 * @project: springboot-security-basic
 * @file: UserMapper.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Viernes 30 Junio 2023, 16:07:30.
 * @description: El presente archivo UserMapper.java fue creado por Abel HZO.
 */
@Mapper(componentModel = ComponentModel.SPRING, uses = { UserRolMapper.class })
public interface UserMapper {

	@Mappings({ 
		@Mapping(target = "userRols", source = "userRolsDTO", qualifiedByName = "customUserRol"),
		@Mapping(target = "creationDate", qualifiedByName = "stringToTimestamp")
	})
	User toUser(UserDTO userDTO);

	@Mappings({ 
		@Mapping(target = "userRolsDTO", source = "userRols", qualifiedByName = "customUserRolDTO"),
		@Mapping(target = "creationDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
	})
	UserDTO toUserDTO(User user);
	
	List<UserDTO> toListUserDTO(List<User> list);
	
}
