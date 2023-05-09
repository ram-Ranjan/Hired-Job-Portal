package com.ty.Hired_JobPortal.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ty.Hired_JobPortal.Entity.Applicant;
import com.ty.Hired_JobPortal.Entity.Employer;
import com.ty.Hired_JobPortal.Entity.Job;
import com.ty.Hired_JobPortal.Entity.JobApplication;
import com.ty.Hired_JobPortal.Entity.Notification;
import com.ty.Hired_JobPortal.Entity.Resume;
import com.ty.Hired_JobPortal.Entity.Skill;

@Component
public class DtoConfig {

	@Autowired
	ApplicantDto applicantDto;
	@Autowired
	JobApplicationDto jobApplicationDto;
	@Autowired
	ResumeDto resumeDto;
	@Autowired
	EmployerDto employerDto;
	@Autowired
	NotificationDto notificationDto;
	@Autowired
	SkillDto skillDto;

	public ApplicantDto setApplicantDtoAttributes(Applicant applicant) {
		applicantDto.setApplicantId(applicant.getApplicantId());
		applicantDto.setApplicantFirstName((applicant.getApplicantFirstName()));
		applicantDto.setApplicantLastName(applicant.getApplicantLastName());
		applicantDto.setApplicantContact(applicant.getApplicantContact());
		applicantDto.setApplicantEmail((applicant.getApplicantEmail()));
		applicantDto.setApplicantAddress(applicant.getApplicantAddress());
		applicantDto.setApplicantPostalCode(applicant.getApplicantPostalCode());
		applicantDto.setApplicantWorkStatus(applicant.getApplicantWorkStatus());
		applicantDto.setApplicantGender(applicant.getApplicantGender());
		return applicantDto;
	}

	public JobApplicationDto setJobApplicationDtoAttributes(JobApplication jobApplication) {
		jobApplicationDto.setJobApplicationId(jobApplication.getJobApplicationId());
		jobApplicationDto.setJobApplicationAppliedDate((jobApplication.getJobApplicationAppliedDate()));
		jobApplicationDto.setJobApplicationNoticePeriodInDays(jobApplication.getJobApplicationNoticePeriodInDays());
		jobApplicationDto.setJobApplicationRefrences(jobApplication.getJobApplicationRefrences());
		jobApplicationDto.setJobApplicationWorkExperience(jobApplication.getJobApplicationWorkExperience());
		return jobApplicationDto;
	}

	public ResumeDto setResumeDtoAttributes(Resume resume) {
		resumeDto.setResumeId(resume.getResumeId());
		resumeDto.setFilePath(resume.getFilePath());
		resumeDto.setUploadDateTime(resume.getUploadDateTime());
		return resumeDto;
	}

	public EmployerDto setEmployerDtoAttributes(Employer employer) {
		employerDto.setEmployerId(employer.getEmployerId());
		employerDto.setEmployerName(employer.getEmployerName());
		employerDto.setEmployerEmail(employer.getEmployerEmail());
		employerDto.setEmployerContact(employer.getEmployerContact());
		return employerDto;
	}

	public JobDto setJobDtoAttributes(Job job) {
		JobDto jobDto = new JobDto();
		jobDto.setJobId(job.getJobId());
		jobDto.setJobName(job.getJobName());
		jobDto.setCompanyName(job.getCompanyName());
		jobDto.setJobDescription(job.getJobDescription());
		jobDto.setJobLocation(job.getJobLocation());
		jobDto.setSalary(job.getSalary());
		jobDto.setDatePosted(job.getDatePosted());
		jobDto.setJobFilled(job.isJobFilled());
		jobDto.setJobCategory(job.getJobCategory());
		jobDto.setEmployer(job.getEmployer());
		return jobDto;

	}

	public NotificationDto setNotificationDtoAttributes(Notification notification) {
		notificationDto.setNotificationId(notification.getNotificationId());
		notificationDto.setNotificationMessage(notification.getNotificationMessage());
		notificationDto.setNotificationTime(notification.getNotificationTime());
		notificationDto.setNotificationType(notification.getNotificationType());
		return notificationDto;
	}

	public SkillDto setSkillDtoAttributes(Skill skill) {
		skillDto.setSkillId(skill.getSkillId());
		skillDto.setSkillName(skill.getSkillName());
		skillDto.setJob(skill.getJobs());
		return skillDto;

	}

}
