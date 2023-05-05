package com.ty.Hired_JobPortal.Exception;

public class NameNotFoundException extends RuntimeException {
	private String message;

	public NameNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
