package com.abelhzo.security.utils;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.abelhzo.security.dtos.ResponseWrapper;
import com.abelhzo.security.dtos.TypeMessage;

/**
 * @author: Abel HZO
 * @project: springboot-security-basic
 * @file: Utils.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Lunes 03 Julio 2023, 21:52:52.
 * @description: El presente archivo Utils.java fue creado por Abel HZO.
 */
@Component
public class Utils {
	
	public ResponseEntity<ResponseWrapper> buildResponseEntity(TypeMessage type, Object obj) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		ResponseWrapper response = new ResponseWrapper();
		response.setTime(LocalDateTime.now().format(formatter));
		response.setTypeMessage(type);
		response.setMessage(type.getMessage());
		response.setBody(obj);

		return new ResponseEntity<ResponseWrapper>(response, type.getStatus());
	}

	public byte[] loadDefaultImageUser() {
		InputStream is = getClass().getResourceAsStream("/userDefault.png");
//		String image = "data:image/png;base64," + is.toString();
		try {
			return is.readAllBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
