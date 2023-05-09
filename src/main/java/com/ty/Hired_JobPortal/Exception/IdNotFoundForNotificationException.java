package com.ty.Hired_JobPortal.Exception;

public class IdNotFoundForNotificationException extends RuntimeException {
	private String message;

	public IdNotFoundForNotificationException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
