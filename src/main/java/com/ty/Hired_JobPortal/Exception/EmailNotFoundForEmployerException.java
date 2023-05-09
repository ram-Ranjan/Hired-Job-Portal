package com.ty.Hired_JobPortal.Exception;

public class EmailNotFoundForEmployerException extends RuntimeException {
	private String message;

	public EmailNotFoundForEmployerException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
