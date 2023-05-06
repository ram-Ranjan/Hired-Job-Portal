package com.ty.Hired_JobPortal.Entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int notificationId;
	@NotBlank(message = "Work Experience shouldn't be blank")
	@NotNull(message = "Work Experience shouln't be null")
	private String notificationMessage;
	@NotBlank(message = "Notification Type shouldn't be blank")
	@NotNull(message = "Notification Type shouln't be null")
	private String notificationType;
	@DateTimeFormat
	private	LocalDateTime notificationTime;
	
	@ManyToOne
	private Employer employer;
	@ManyToOne 
	private Applicant applicant;
	
}
