package com.ty.Hired_JobPortal.Exception;

import lombok.Getter;

@Getter
public class EmailNotFoundException extends RuntimeException {

	private String message;

	public EmailNotFoundException(String message) {
		this.message = message;
	}

}
