package com.ty.Hired_JobPortal.Exception;

import lombok.Getter;

@Getter
public class ApplicantCredentialsInvalidException extends RuntimeException {

	private String message;

	public ApplicantCredentialsInvalidException(String message) {
		this.message = message;
	}

}
