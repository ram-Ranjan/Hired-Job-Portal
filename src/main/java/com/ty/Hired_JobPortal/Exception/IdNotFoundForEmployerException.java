package com.ty.Hired_JobPortal.Exception;

public class IdNotFoundForEmployerException extends RuntimeException {
	
	private String message;

	public IdNotFoundForEmployerException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
