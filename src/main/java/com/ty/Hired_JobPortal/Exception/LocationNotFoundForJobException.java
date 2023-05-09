package com.ty.Hired_JobPortal.Exception;

public class LocationNotFoundForJobException extends RuntimeException {
	private String message;

	public LocationNotFoundForJobException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
