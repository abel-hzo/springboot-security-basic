package com.abelhzo.security.repositories;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abelhzo.security.entities.UserRol;

import jakarta.transaction.Transactional;

/**
 * @author: Abel HZO
 * @project: springboot-security-basic
 * @file: UserRolRepository.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Sábado 01 Julio 2023, 03:44:01.
 * @description: El presente archivo UserRolRepository.java fue creado por Abel HZO.
 */
@Repository
public interface UserRolRepository extends JpaRepository<UserRol, Long> {
	
	@Query(value = "SELECT * FROM USERSROLES WHERE ID_USER = :idUser AND ID_ROL = :idRol", nativeQuery = true)
	UserRol existsUserRol(@Param("idUser") Long idUser, @Param("idRol") Integer idRol);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO USERSROLES (ID_USER, ID_ROL, CREATION_DATE ) VALUES (:idUser, :idRol, :creationDate)", nativeQuery = true)
	void addUserRol(@Param("idUser") Long idUser, @Param("idRol") Integer idRol, @Param("creationDate") Timestamp creationDate);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM USERSROLES WHERE ID_USER = :idUser AND ID_ROL = :idRol", nativeQuery = true)
	void removeUserRol(@Param("idUser") Long idUser, @Param("idRol") Integer idRol);
}
