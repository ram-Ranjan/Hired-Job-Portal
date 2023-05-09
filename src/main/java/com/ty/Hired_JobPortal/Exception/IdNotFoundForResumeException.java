package com.ty.Hired_JobPortal.Exception;

public class IdNotFoundForResumeException extends RuntimeException {
	private String message;

	public IdNotFoundForResumeException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
