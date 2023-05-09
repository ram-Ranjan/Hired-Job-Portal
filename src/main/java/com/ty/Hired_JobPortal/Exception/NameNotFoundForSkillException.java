package com.ty.Hired_JobPortal.Exception;

public class NameNotFoundForSkillException extends RuntimeException {
	private String message;

	public NameNotFoundForSkillException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
