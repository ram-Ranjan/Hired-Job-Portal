package com.ty.Hired_JobPortal.Exception;

public class IdNotFoundForJobApplicationException extends RuntimeException {
	private String message;

	public IdNotFoundForJobApplicationException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
