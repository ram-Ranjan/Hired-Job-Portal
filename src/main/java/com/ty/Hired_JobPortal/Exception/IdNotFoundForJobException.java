package com.ty.Hired_JobPortal.Exception;

public class IdNotFoundForJobException extends RuntimeException {
	private String message;

	public IdNotFoundForJobException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
