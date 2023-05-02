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
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int notificationId;
	private String notificationMessage;
	private String notificationType;
	private	LocalDateTime notificationTime;
	
	@ManyToOne
	private Employer employer;
	@ManyToOne
	private Applicant applicant;
	@OneToOne
	private JobApplicantion jobApplication;
}
