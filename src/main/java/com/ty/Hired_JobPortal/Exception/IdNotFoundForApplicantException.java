package com.ty.Hired_JobPortal.Exception;

public class IdNotFoundForApplicantException extends RuntimeException {
	private String message;

	public IdNotFoundForApplicantException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
