package com.ty.Hired_JobPortal.DTO;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ApplicantDto {
	private int applicantId;
	private String applicantFirstName;
	private String applicantLastName;
	private String applicantEmail;
	private long applicantContact;
	private String applicantAddress;

}
