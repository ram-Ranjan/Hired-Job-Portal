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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/notification")
public class NotificationController {
	@Autowired
	private NotificationService notificationService;

	@ApiOperation(value = "Save Notification", notes = "API is used to save Notification ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully created"),
			@ApiResponse(code = 400, message = "Id not found for the given Notification ID") })
	@PostMapping
	public ResponseEntity<ResponseStructure<NotificationDto>> addNotification(@RequestBody Notification notification) {
		return notificationService.addNotification(notification);
	}

	@ApiOperation(value = "Get Notification", notes = "API is used to save Notification ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully found"),
			@ApiResponse(code = 400, message = "Id not found for the given Notification ID") })
	@GetMapping
	public ResponseEntity<ResponseStructure<NotificationDto>> getNotification(@RequestParam int notificationId) {
		return notificationService.getNotification(notificationId);
	}

	@ApiOperation(value = "Update Notification", notes = "API is used to save Notification ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully updated"),
			@ApiResponse(code = 400, message = "Id not found for the given Notification ID") })
	@PutMapping
	public ResponseEntity<ResponseStructure<NotificationDto>> updateNotification(@RequestBody Notification notification,
			@RequestParam int notificationId) {
		return notificationService.updateNotification(notification, notificationId);
	}

	@ApiOperation(value = "Delete Notification", notes = "API is used to save Notification ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully deleted"),
			@ApiResponse(code = 400, message = "Id not found for the given Notification ID") })
	@DeleteMapping
	public ResponseEntity<ResponseStructure<NotificationDto>> deleteNotification(@RequestParam int notificationId) {
		return notificationService.deleteNotification(notificationId);
	}
}
