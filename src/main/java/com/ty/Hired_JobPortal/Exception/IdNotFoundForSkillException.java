package com.ty.Hired_JobPortal.Exception;

public class IdNotFoundForSkillException extends RuntimeException {
	private String message;

	public IdNotFoundForSkillException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
