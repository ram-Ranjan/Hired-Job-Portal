package com.ty.Hired_JobPortal.DTO;

import org.springframework.beans.factory.annotation.Autowired;

import com.ty.Hired_JobPortal.Entity.Applicant;

public class DtoConfig {
	
	
	@Autowired
	ApplicantDto applicantDto;
	
	
	public ApplicantDto setApplicantDtoAttributes(Applicant applicant)
	{
		applicantDto.setApplicantFirstName((applicant.getApplicantFirstName()));
		applicantDto.setApplicantLastName(applicant.getApplicantLastName());
		applicantDto.setApplicantContact(applicant.getApplicantContact());
		applicantDto.setApplicantEmail((applicant.getApplicantEmail()));
		applicantDto.setApplicantAddress(applicant.getApplicantAddress());
		return applicantDto;
	}

}
