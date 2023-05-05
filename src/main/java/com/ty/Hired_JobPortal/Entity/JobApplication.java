package com.ty.Hired_JobPortal.Entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class JobApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int jobApplicationId;
	private LocalDateTime jobApplicationAppliedDate;
	private int jobApplicationNoticePeriodInDays;
	private String jobApplicationRefrences;
	private String jobApplicationWorkExperience;
	
	
	@OneToOne
	private Notification notification;
	@OneToOne
	private Resume resume;
	
	
}
