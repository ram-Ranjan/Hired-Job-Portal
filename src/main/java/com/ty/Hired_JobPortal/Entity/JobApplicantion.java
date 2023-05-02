package com.ty.Hired_JobPortal.Entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class JobApplicantion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int jobApplicationId;
	private LocalDateTime appliedDate;
	private int noticePeriod;
	
	@OneToOne
	private Notification notification;
	@OneToOne
	private Resume resume;
	@ManyToOne
	private Applicant applicant;
}
