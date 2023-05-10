package com.ty.Hired_JobPortal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.Hired_JobPortal.Config.ResponseStructure;
import com.ty.Hired_JobPortal.DAO.ApplicantDao;
import com.ty.Hired_JobPortal.DAO.EmployerDao;
import com.ty.Hired_JobPortal.DAO.NotificationDao;
import com.ty.Hired_JobPortal.DTO.DtoConfig;
import com.ty.Hired_JobPortal.DTO.NotificationDto;
import com.ty.Hired_JobPortal.Entity.Applicant;
import com.ty.Hired_JobPortal.Entity.Employer;
import com.ty.Hired_JobPortal.Entity.Notification;
import com.ty.Hired_JobPortal.Exception.IdNotFoundForApplicantException;
import com.ty.Hired_JobPortal.Exception.IdNotFoundForEmployerException;
import com.ty.Hired_JobPortal.Exception.IdNotFoundForNotificationException;

@Service
public class NotificationService {
	@Autowired
	private NotificationDao notificationDao;
	@Autowired
	private NotificationDto notificationDto;
	@Autowired
	private DtoConfig dtoConfig;
	@Autowired
	ApplicantDao applicantDao;
	@Autowired
	EmployerDao employerDao;

	public ResponseEntity<ResponseStructure<List<Notification>>> getNotificationByApplicantId(int applicantId) {

		Applicant applicant = applicantDao.findApplicantById(applicantId);
		if (applicant != null) {
			List<Notification> existingNotifications = notificationDao.findNotificationByApplicant(applicant);
			if (existingNotifications != null) {
				for (Notification notification : existingNotifications) {
					notificationDto = dtoConfig.setNotificationDtoAttributes(notification);
					notificationDto.setApplicant(applicant);
				}
				ResponseStructure<List<Notification>> responseStructure = new ResponseStructure<>();
				responseStructure.setStatus(HttpStatus.FOUND.value());
				responseStructure.setMessage("Notification Found!!");
				responseStructure.setData(existingNotifications);
				return new ResponseEntity<ResponseStructure<List<Notification>>>(responseStructure, HttpStatus.CREATED);
			} else
				throw new IdNotFoundForNotificationException("Failed to find the Notification!!");
		} else
			throw new IdNotFoundForApplicantException("Failed to find the Applicant Id!!");
	}

	public ResponseEntity<ResponseStructure<List<Notification>>> getNotificationbyEmployerId(int employerId) {

		Employer employer = employerDao.findEmployerById(employerId);
		if (employer != null) {
			List<Notification> existingNotifications = notificationDao.findNotificationByEmployer(employer);
			if (existingNotifications != null) {
				for (Notification notification : existingNotifications) {
					notificationDto = dtoConfig.setNotificationDtoAttributes(notification);
					notificationDto.setEmployer(employer);
				}
				ResponseStructure<List<Notification>> responseStructure = new ResponseStructure<>();
				responseStructure.setStatus(HttpStatus.FOUND.value());
				responseStructure.setMessage("Notification Found!!");
				responseStructure.setData(notificationDto);
				return new ResponseEntity<ResponseStructure<List<Notification>>>(responseStructure, HttpStatus.CREATED);
			} else {
				throw new IdNotFoundForNotificationException("Failed to find the Notification!!");
			}
		} else {
			throw new IdNotFoundForEmployerException("Failed to find the Employer!!");
		}
	}

	public ResponseEntity<ResponseStructure<NotificationDto>> deleteNotification(int notificationId) {
		Notification existingNotification = notificationDao.deleteNotificationById(notificationId);

		if (existingNotification != null) {
			existingNotification.setApplicant(null);
			existingNotification.setEmployer(null);
			notificationDto = dtoConfig.setNotificationDtoAttributes(existingNotification);
			ResponseStructure<NotificationDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Notification Deleted!!");
			responseStructure.setData(notificationDto);
			return new ResponseEntity<ResponseStructure<NotificationDto>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new IdNotFoundForNotificationException("Failed to Delete the Notification!!");
		}
	}
}
