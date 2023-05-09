package com.ty.Hired_JobPortal.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.Hired_JobPortal.Entity.Applicant;
import com.ty.Hired_JobPortal.Entity.Employer;
import com.ty.Hired_JobPortal.Entity.Notification;

public interface NotificationRepo extends JpaRepository<Notification, Integer> {
	
	
	@Query("select n from Notification n where n.applicant=?1")
	public Optional<List<Notification>> getNotificationByApplicant(Applicant applicant);
	
	@Query("select n from Notification n where n.employer =?1")
	public  Optional<List<Notification>> getNotificationByEmployer(Employer employer);
	
	
	
}
