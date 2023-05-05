package com.ty.Hired_JobPortal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.Hired_JobPortal.Config.ResponseStructure;
import com.ty.Hired_JobPortal.DAO.NotificationDao;
import com.ty.Hired_JobPortal.DTO.NotificationDto;
import com.ty.Hired_JobPortal.Entity.Notification;
import com.ty.Hired_JobPortal.Exception.IdNotFoundException;

@Service
public class NotificationService {
	@Autowired
	private NotificationDao notificationDao;

	@Autowired
	private NotificationDto notificationDto;

	public ResponseEntity<ResponseStructure<NotificationDto>> addNotification(Notification notification) {
		ResponseStructure<NotificationDto> responseStructure = new ResponseStructure<>();
		Notification notification2 = notificationDao.addNotification(notification);

		responseStructure.setStatus(notification2.getNotificationId());
		responseStructure.setMessage("Notification not Added!!");
		notificationDto.setNotificationId(notification2.getNotificationId());
		notificationDto.setNotificationType(notification2.getNotificationType());
		notificationDto.setNotificationMessage(notification2.getNotificationMessage());
		notificationDto.setNotificationTime(notification2.getNotificationTime());
		notificationDto.setEmployer(notification2.getEmployer());
		notificationDto.setApplicant(notification2.getApplicant());
		notificationDto.setJobApplication(notification2.getJobApplication());
		responseStructure.setData(notification2);
		return new ResponseEntity<ResponseStructure<NotificationDto>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<NotificationDto>> getNotification(int notificationId) {
		ResponseStructure<NotificationDto> responseStructure = new ResponseStructure<>();
		Notification existingNotification = notificationDao.findNotificationById(notificationId);

		if (existingNotification != null) {
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Notification Found!!");
			notificationDto.setNotificationId(existingNotification.getNotificationId());
			notificationDto.setNotificationType(existingNotification.getNotificationType());
			notificationDto.setNotificationMessage(existingNotification.getNotificationMessage());
			notificationDto.setNotificationTime(existingNotification.getNotificationTime());
			notificationDto.setEmployer(existingNotification.getEmployer());
			notificationDto.setApplicant(existingNotification.getApplicant());
			notificationDto.setJobApplication(existingNotification.getJobApplication());
			responseStructure.setData(existingNotification);
			return new ResponseEntity<ResponseStructure<NotificationDto>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new IdNotFoundException("Failed to find the Notification!!");
		}
	}

	public ResponseEntity<ResponseStructure<NotificationDto>> updateNotification(Notification updatedNotification,
			int notificationId) {
		ResponseStructure<NotificationDto> responseStructure = new ResponseStructure<>();
		Notification existingNotification = notificationDao.findNotificationById(notificationId);

		if (existingNotification != null) {
			updatedNotification.setNotificationId(existingNotification.getNotificationId());
			existingNotification = notificationDao.updateNotification(updatedNotification);
			notificationDto.setNotificationId(existingNotification.getNotificationId());
			notificationDto.setNotificationType(existingNotification.getNotificationType());
			notificationDto.setNotificationMessage(existingNotification.getNotificationMessage());
			notificationDto.setNotificationTime(existingNotification.getNotificationTime());
			notificationDto.setEmployer(existingNotification.getEmployer());
			notificationDto.setApplicant(existingNotification.getApplicant());
			notificationDto.setJobApplication(existingNotification.getJobApplication());
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Notification Updated!!");
			responseStructure.setData(notificationDto);
			return new ResponseEntity<ResponseStructure<NotificationDto>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new IdNotFoundException("Failed to Update the Notification!!");
		}
	}

	public ResponseEntity<ResponseStructure<NotificationDto>> deleteNotification(int notificationId) {
		ResponseStructure<NotificationDto> responseStructure = new ResponseStructure<>();
		Notification existingNotification = notificationDao.deleteNotificationById(notificationId);

		if (existingNotification != null) {
			notificationDto.setNotificationId(existingNotification.getNotificationId());
			notificationDto.setNotificationType(existingNotification.getNotificationType());
			notificationDto.setNotificationMessage(existingNotification.getNotificationMessage());
			notificationDto.setNotificationTime(existingNotification.getNotificationTime());
			notificationDto.setEmployer(existingNotification.getEmployer());
			notificationDto.setApplicant(existingNotification.getApplicant());
			notificationDto.setJobApplication(existingNotification.getJobApplication());
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Notification Deleted!!");
			responseStructure.setData(notificationDto);
			return new ResponseEntity<ResponseStructure<NotificationDto>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new IdNotFoundException("Failed to Delete the Notification!!");
		}
	}
}
