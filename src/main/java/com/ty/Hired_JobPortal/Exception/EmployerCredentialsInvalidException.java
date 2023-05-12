package com.ty.Hired_JobPortal.Exception;

import lombok.Getter;

@Getter
public class EmployerCredentialsInvalidException extends RuntimeException {

	private String message;

	public EmployerCredentialsInvalidException(String message) {
		this.message = message;
	}

}
