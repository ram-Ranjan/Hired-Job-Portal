package com.ty.Hired_JobPortal.DTO;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class NotificationDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int notificationId;
	private String notificationMessage;
	private String notificationType;
	private	LocalDateTime notificationTime;
	
}
