package com.ty.Hired_JobPortal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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


	@ApiOperation(value = "Get Notification by Applicant ", notes = "API is used to save Notification ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully found"),
			@ApiResponse(code = 400, message = "Id not found for the given Notification ID") })
	@GetMapping("/applicant")
	public ResponseEntity<ResponseStructure<List<Notification>>> getNotificationByApplicantId(@RequestParam int applicantId) {
		return notificationService.getNotificationByApplicantId(applicantId);
	}
	

	@ApiOperation(value = "Get Notification by Employer", notes = "API is used to save Notification ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully found"),
			@ApiResponse(code = 400, message = "Id not found for the given Notification ID") })
	@GetMapping("/employer")
	public ResponseEntity<ResponseStructure<List<Notification>>> getNotificationByEmployerID(@RequestParam int employerId) {
		return notificationService.getNotificationByApplicantId(employerId);
	}


	@ApiOperation(value = "Delete Notification", notes = "API is used to save Notification ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully deleted"),
			@ApiResponse(code = 400, message = "Id not found for the given Notification ID") })
	@DeleteMapping
	public ResponseEntity<ResponseStructure<NotificationDto>> deleteNotification(@RequestParam int notificationId) {
		return notificationService.deleteNotification(notificationId);
	}
}
