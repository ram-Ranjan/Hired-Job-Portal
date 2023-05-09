package com.ty.Hired_JobPortal.Exception;

public class NameNotFoundForCompanyException extends RuntimeException {
	private String message;

	public NameNotFoundForCompanyException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
