package com.ty.Hired_JobPortal.Entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
