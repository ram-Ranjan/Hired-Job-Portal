package com.ty.Hired_JobPortal.DTO;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import com.ty.Hired_JobPortal.Entity.Applicant;
import com.ty.Hired_JobPortal.Entity.Employer;
import com.ty.Hired_JobPortal.Entity.Skill;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class JobDto {
	private int jobId;
	private String jobName;
	private String jobDescription;
	private String companyName;
	private String jobLocation;
	private double salary;
	private LocalDate datePosted;
	private boolean isFilled;
	private String jobCategory;
	
	@ManyToOne
	private Employer employer;
	@ManyToMany
	private List<Applicant> applicant;
	@OneToMany
	private List<Skill> skill;

}
