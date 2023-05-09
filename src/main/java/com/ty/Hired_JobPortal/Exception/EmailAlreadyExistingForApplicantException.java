package com.ty.Hired_JobPortal.Exception;

import lombok.Getter;

@Getter
public class EmailAlreadyExistingForApplicantException extends RuntimeException {

	private String message;

	public EmailAlreadyExistingForApplicantException(String message) {
		this.message = message;
	}

}
