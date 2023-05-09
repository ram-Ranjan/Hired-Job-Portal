package com.ty.Hired_JobPortal.Exception;

public class EmailAlreadyExistingForEmployerException extends RuntimeException {
	private String message;

	public EmailAlreadyExistingForEmployerException(String message) {
		this.message = message;
	}
}
