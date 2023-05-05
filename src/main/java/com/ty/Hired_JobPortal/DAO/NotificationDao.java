package com.ty.Hired_JobPortal.DAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.Hired_JobPortal.Entity.Notification;
import com.ty.Hired_JobPortal.Repo.NotificationRepo;

@Repository
public class NotificationDao {
	@Autowired
	private NotificationRepo notificationRepo;
	
	public Notification addNotification(Notification notification) {
		return notificationRepo.save(notification);
	}
	
	public Notification findNotificationById(int notificationId) {
		Optional<Notification> optional = notificationRepo.findById(notificationId);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
	
	public Notification updateNotification(Notification notification) {
		return notificationRepo.save(notification);
	}
	
	public Notification deleteNotificationById(int notificationId) {
		Optional<Notification> optional = notificationRepo.findById(notificationId);
		if(optional.isEmpty()) {
			return null;
		}
		else {
			Notification notification = optional.get();
			notificationRepo.delete(notification);
			return notification;
		}
	}
}
