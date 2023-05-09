package com.ty.Hired_JobPortal.Entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class JobApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int jobApplicationId;
	@DateTimeFormat
	private LocalDateTime jobApplicationAppliedDate;
	@PositiveOrZero
	private int noticePeriod;
	@NotBlank(message = "Job Reference shouldn't be blank")
	@NotNull(message = "Job Reference shouldn't be null")
	private String jobApplicationReference;
	@NotBlank(message = "Work Experience shouldn't be blank")
	@NotNull(message = "Work Experience shouldn't be null")
	private String jobApplicationWorkExperience;
	

	@ManyToOne(cascade = CascadeType.ALL)
	private Job job;
	

	@ManyToOne
	@JoinColumn
	private Resume resume;

}
