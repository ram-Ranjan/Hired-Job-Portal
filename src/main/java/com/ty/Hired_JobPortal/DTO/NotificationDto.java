package com.ty.Hired_JobPortal.DTO;

import java.time.LocalDateTime;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

import com.ty.Hired_JobPortal.Entity.Applicant;
import com.ty.Hired_JobPortal.Entity.Employer;
import com.ty.Hired_JobPortal.Entity.JobApplicantion;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class NotificationDto {
	private int notificationId;
	private String notificationMessage;
	private String notificationType;
	private LocalDateTime notificationTime;


}
