package com.ty.Hired_JobPortal.Exception;

public class EmailNotFoundForApplicantException extends RuntimeException {
	private String message;

	public EmailNotFoundForApplicantException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
