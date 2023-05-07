package com.ty.Hired_JobPortal.DTO;

import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

import com.ty.Hired_JobPortal.Entity.Job;
import com.ty.Hired_JobPortal.Entity.JobApplication;
import com.ty.Hired_JobPortal.Entity.Notification;
import com.ty.Hired_JobPortal.Entity.Resume;
import com.ty.Hired_JobPortal.Entity.Skill;

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
	private int applicantPostalCode;
	private String applicantWorkStatus;
	private String applicantGender;
 	private String highestQualification;
	
	@OneToMany
	private List<Skill> skill;
	
	@OneToOne 
	private Resume resume;
	
	@OneToMany
	private List<JobApplication> jobApplication;
	
	@OneToMany
	private List<Notification> notification;
	
	@ManyToMany
	private List<Job> job;

}
