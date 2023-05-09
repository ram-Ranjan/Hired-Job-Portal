package com.ty.Hired_JobPortal.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.Hired_JobPortal.Entity.Applicant;
import com.ty.Hired_JobPortal.Entity.Employer;
import com.ty.Hired_JobPortal.Entity.Notification;
import com.ty.Hired_JobPortal.Repo.NotificationRepo;

@Repository
public class NotificationDao {
	@Autowired
	private NotificationRepo notificationRepo;

	public Notification addNotification(Notification notification) {
		return notificationRepo.save(notification);
	}

	public List<Notification> findNotificationByApplicant(Applicant applicant) {
		Optional<List<Notification>> optional = notificationRepo.getNotificationByApplicant(applicant);
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}
	
	public List<Notification> findNotificationByEmployer(Employer employer) {
		Optional<List<Notification>> optional = notificationRepo.getNotificationByEmployer(employer);
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}

	public Notification deleteNotificationById(int notificationId) {
		Optional<Notification> optional = notificationRepo.findById(notificationId);
		if (optional.isEmpty()) {
			return null;
		} else {
			Notification notification = optional.get();
			notificationRepo.delete(notification);
			return notification;
		}
	}
}
