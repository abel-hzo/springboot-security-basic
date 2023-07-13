package com.abelhzo.security.dtos;

import org.springframework.http.HttpStatus;

/**
 * @author: Abel HZO
 * @project: springboot-security-basic
 * @file: TypeMessage.java
 * @location: México, Ecatepec, Edo. de México.
 * @date: Viernes 30 Junio 2023, 16:56:22.
 * @description: El presente archivo TypeMessage.java fue creado por Abel HZO.
 */
public enum TypeMessage {

	SUCCESS("Operación realizada con exito.", HttpStatus.OK),
	
	BAD_REQUEST("Datos invalidos", HttpStatus.BAD_REQUEST),

	NOT_FOUND("No hay datos para esta consulta.", HttpStatus.NO_CONTENT),
	
	DUPLICATED("No pueden guardarse datos existentes.", HttpStatus.CONFLICT),

	FAILURE("No se puedo realizar la operación.", HttpStatus.EXPECTATION_FAILED),

	INVALID_LOGIN("Nombre de usuario o contraseña invalidos.", HttpStatus.UNAUTHORIZED);

	private final String message;
	private final HttpStatus status;

	TypeMessage(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatus() {
		return status;
	}

}
