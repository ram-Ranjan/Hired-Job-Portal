package com.ty.Hired_JobPortal.DTO;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ty.Hired_JobPortal.Entity.Applicant;
import com.ty.Hired_JobPortal.Entity.Employer;
import com.ty.Hired_JobPortal.Entity.JobApplication;
import com.ty.Hired_JobPortal.Entity.Skill;

import lombok.Getter;
import lombok.Setter;

@Component
@Scope(value = "prototype")
@Getter
@Setter
public class JobDto {
	private int jobId;
	private String jobName;
	private String jobDescription;
	private String companyName;
	private String jobLocation;
	private String salary;
	private LocalDate datePosted;
	private String jobStatus;
	private String jobCategory;

	@ManyToOne
	@JoinColumn(name = "employerId")
	private Employer employer;

	@ManyToMany
	@JoinTable
	private List<Applicant> applicant;

	@ManyToMany
	private List<Skill> skill;

	@OneToMany
	private List<JobApplication> jobApplication;

}
