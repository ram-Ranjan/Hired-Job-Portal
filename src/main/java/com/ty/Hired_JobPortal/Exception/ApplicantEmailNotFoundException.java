package com.ty.Hired_JobPortal.Exception;

import lombok.Getter;

@Getter
public class ApplicantEmailNotFoundException extends RuntimeException {

	private String message;

	public ApplicantEmailNotFoundException(String message) {
		this.message = message;
	}

}
