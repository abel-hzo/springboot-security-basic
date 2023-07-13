package com.abelhzo.security.repositories;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abelhzo.security.entities.User;

import jakarta.transaction.Transactional;

/**
 * @author: Abel HZO
 * @project: springboot-security-basic
 * @file: UserRepository.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Viernes 30 Junio 2023, 16:02:55.
 * @description: El presente archivo UserRepository.java fue creado por Abel HZO.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE USERS SET "
			+ "USERNAME = :username, "
			+ "PASSWORD = :password, "
			+ "BIRTHDAY = :birthday "
			+ "WHERE ID_USER = :idUser", nativeQuery = true)
	void modifyUser(@Param("username") String username, 
			        @Param("password") String password, 
			        @Param("birthday") Date bithday, 
			        @Param("idUser") Long idUser);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE USERS SET PHOTO = :photo WHERE ID_USER = :idUser", nativeQuery = true)
	void modifyPhoto(@Param("photo") byte[] photo, @Param("idUser") Long idUser);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE USERS SET PASSWORD = :pass WHERE ID_USER = :idUser", nativeQuery = true)
	void changeAllPassword(@Param("pass") String password, @Param("idUser") Long idUser);

}
