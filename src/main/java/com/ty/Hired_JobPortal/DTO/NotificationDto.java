package com.ty.Hired_JobPortal.DTO;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
@Component
@Getter
@Setter
public class NotificationDto {
	private int notificationId;
	private String notificationMessage;
	private String notificationType;
	private	LocalDateTime notificationTime;
	
}
