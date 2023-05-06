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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int jobId;
	@NotBlank(message = "Job Name shouldn't be blank")
	@NotNull(message = "Job Name shouln't be null")
	private String jobName;
	@NotBlank(message = "Job Description shouldn't be blank")
	@NotNull(message = "Job Description shouln't be null")
	private String jobDescription;
	@NotBlank(message = "Company Name shouldn't be blank")
	@NotNull(message = "Company Name shouln't be null")
	private String companyName;
	@NotBlank(message = "Job Location shouldn't be blank")
	@NotNull(message = "Job Location shouln't be null")
	private String jobLocation;
	@NotBlank(message = "Job Salary shouldn't be blank")
	@NotNull(message = "Job Salary shouln't be null")
	private String salary;
	@PastOrPresent(message = "Date posted is invalid ")
	private LocalDate datePosted;
	@NotNull(message = "isFilled shouln't be null")
	private boolean isFilled;
	@NotBlank(message = "Job Category shouldn't be blank")
	@NotNull(message = "Job Category shouln't be null")
	private String jobCategory;

	@ManyToOne
	private Employer employer;
	@ManyToMany
	private List<Applicant> applicant;
	@OneToMany
	private List<Skill> skill;

}
