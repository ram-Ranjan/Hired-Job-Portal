package com.ty.Hired_JobPortal.Exception;

public class NameNotFoundForEmployerException extends RuntimeException {
	private String message;

	public NameNotFoundForEmployerException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
