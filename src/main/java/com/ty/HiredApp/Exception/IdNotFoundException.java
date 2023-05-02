package com.ty.HiredApp.Exception;

public class IdNotFoundException extends RuntimeException {
	private String message;

	public IdNotFoundException(String message) {
		this.message = message;
	}
}
