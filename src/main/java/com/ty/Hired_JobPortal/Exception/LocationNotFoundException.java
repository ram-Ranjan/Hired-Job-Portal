package com.ty.Hired_JobPortal.Exception;

public class LocationNotFoundException extends Exception {
	private String message;

	public LocationNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
