package com.ty.Hired_JobPortal.DTO;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ApplicantDto {
	private int applicantId;
	private String applicantFirstName;
	private String applicantLastName;
	private String applicantEmail;
	private String applicantPassword;
	private long applicantContact;
	private String applicantAddress;

}
