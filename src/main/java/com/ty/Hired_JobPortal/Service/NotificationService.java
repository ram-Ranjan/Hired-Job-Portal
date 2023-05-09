package com.ty.Hired_JobPortal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.Hired_JobPortal.Config.ResponseStructure;
import com.ty.Hired_JobPortal.DAO.NotificationDao;
import com.ty.Hired_JobPortal.DTO.DtoConfig;
import com.ty.Hired_JobPortal.DTO.NotificationDto;
import com.ty.Hired_JobPortal.Entity.Notification;
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

	public ResponseEntity<ResponseStructure<NotificationDto>> addNotification(Notification notification) {
		notification = notificationDao.addNotification(notification);

		notificationDto = dtoConfig.setNotificationDtoAttributes(notification);
		ResponseStructure<NotificationDto> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Notification not Added!!");
		responseStructure.setData(notification);
		return new ResponseEntity<ResponseStructure<NotificationDto>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<NotificationDto>> getNotification(int notificationId) {
		Notification existingNotification = notificationDao.findNotificationById(notificationId);

		if (existingNotification != null) {
			notificationDto = dtoConfig.setNotificationDtoAttributes(existingNotification);

			ResponseStructure<NotificationDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Notification Found!!");
			responseStructure.setData(notificationDto);
			return new ResponseEntity<ResponseStructure<NotificationDto>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new IdNotFoundForNotificationException("Failed to find the Notification!!");
		}
	}

	public ResponseEntity<ResponseStructure<NotificationDto>> updateNotification(Notification updatedNotification,
			int notificationId) {
		Notification existingNotification = notificationDao.findNotificationById(notificationId);

		if (existingNotification != null) {
			updatedNotification.setNotificationId(existingNotification.getNotificationId());
			existingNotification = notificationDao.updateNotification(updatedNotification);
			notificationDto = dtoConfig.setNotificationDtoAttributes(existingNotification);

			ResponseStructure<NotificationDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Notification Updated!!");
			responseStructure.setData(notificationDto);
			return new ResponseEntity<ResponseStructure<NotificationDto>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new IdNotFoundForNotificationException("Failed to Update the Notification!!");
		}
	}

	public ResponseEntity<ResponseStructure<NotificationDto>> deleteNotification(int notificationId) {
		Notification existingNotification = notificationDao.deleteNotificationById(notificationId);

		if (existingNotification != null) {
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
