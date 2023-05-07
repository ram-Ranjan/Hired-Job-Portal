package com.ty.Hired_JobPortal.Exception;

public class EmployerNameNotFoundException extends RuntimeException {

	private String message;

	public EmployerNameNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
