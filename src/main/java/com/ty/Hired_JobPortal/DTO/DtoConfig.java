package com.ty.Hired_JobPortal.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ty.Hired_JobPortal.Entity.Applicant;
import com.ty.Hired_JobPortal.Entity.JobApplication;
import com.ty.Hired_JobPortal.Entity.Resume;

@Component
public class DtoConfig {

	@Autowired
	ApplicantDto applicantDto;

	@Autowired
	JobApplicationDto jobApplicationDto;
	@Autowired
	ResumeDto resumeDto;

	public ApplicantDto setApplicantDtoAttributes(Applicant applicant) {
		applicantDto.setApplicantId(applicant.getApplicantId());
		applicantDto.setApplicantFirstName((applicant.getApplicantFirstName()));
		applicantDto.setApplicantLastName(applicant.getApplicantLastName());
		applicantDto.setApplicantContact(applicant.getApplicantContact());
		applicantDto.setApplicantEmail((applicant.getApplicantEmail()));
		applicantDto.setApplicantAddress(applicant.getApplicantAddress());
		return applicantDto;
	}

	public JobApplicationDto setJobApplicationDtoAttributes(JobApplication jobApplication) {
		jobApplicationDto.setJobApplicationId(jobApplication.getJobApplicationId());
		jobApplicationDto.setAppliedDate((jobApplication.getAppliedDate()));
		jobApplicationDto.setNoticePeriodInDays(jobApplication.getNoticePeriodInDays());
		return jobApplicationDto;
	}

	public ResumeDto setResumeDtoAttributes(Resume resume) {
		resumeDto.setResumeId(resume.getResumeId());
		resumeDto.setFilePath(resume.getFilePath());
		resumeDto.setUploadDate(resume.getUploadDate());
		return resumeDto;
	}

}
