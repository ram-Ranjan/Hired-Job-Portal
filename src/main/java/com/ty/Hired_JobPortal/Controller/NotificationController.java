package com.ty.Hired_JobPortal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.Hired_JobPortal.Config.ResponseStructure;
import com.ty.Hired_JobPortal.DTO.NotificationDto;
import com.ty.Hired_JobPortal.Entity.Notification;
import com.ty.Hired_JobPortal.Service.NotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController {
	@Autowired
	private NotificationService notificationService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<NotificationDto>> addNotification(@RequestBody Notification notification){
		return notificationService.addNotification(notification);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<NotificationDto>> getNotification(@RequestParam int notificationId){
		return notificationService.getNotification(notificationId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<NotificationDto>> updateNotification(@RequestBody Notification notification,@RequestParam int notificationId){
		return notificationService.updateNotification(notification, notificationId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<NotificationDto>> deleteNotification(@RequestParam int notificationId){
		return notificationService.deleteNotification(notificationId);
	}
}
