package com.ty.Hired_JobPortal.Exception;

import lombok.Getter;

@Getter
public class ApplicantAlreadyExistingWithEmail extends RuntimeException {

	private String message;

	public ApplicantAlreadyExistingWithEmail(String message) {
		this.message = message;
	}

}
