package com.ty.Hired_JobPortal.Exception;

import lombok.Getter;

@Getter
public class EmailAlreadyExistingException extends RuntimeException {

	private String message;

	public EmailAlreadyExistingException(String message) {
		this.message = message;
	}

}
