package com.ty.Hired_JobPortal.Entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	private int jobApplicationNoticePeriodInDays;
	@NotBlank(message = "Job Application shouldn't be blank")
	@NotNull(message = "Job Application shouldn't be null")
	private String jobApplicationRefrences;
	@NotBlank(message = "Work Experience shouldn't be blank")
	@NotNull(message = "Work Experience shouldn't be null")
	private String jobApplicationWorkExperience;
	
	
	@OneToOne 
	private Notification notification;
	@OneToOne
	private Job job;
	

	@ManyToOne
	@JoinColumn
	private Resume resume;

}
