package com.ty.Hired_JobPortal.Exception;

public class NameNotFoundForJobException extends RuntimeException {
	private String message;

	public NameNotFoundForJobException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
