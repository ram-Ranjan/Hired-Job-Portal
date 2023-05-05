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
import com.ty.Hired_JobPortal.DTO.JobApplicationDto;
import com.ty.Hired_JobPortal.Entity.JobApplication;
import com.ty.Hired_JobPortal.Service.JobApplicationService;

@RestController
@RequestMapping("/jobApplication")
public class JobApplicationController {
	@Autowired
	private JobApplicationService jobApplicationService;

	@PostMapping
	public ResponseEntity<ResponseStructure<JobApplicationDto>> addJobApplication(
			@RequestBody JobApplication jobApplication,@RequestParam int applicantId) {
		return jobApplicationService.addJobApplication(jobApplication,applicantId);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<JobApplicationDto>> getJobApplication(@RequestParam int jobApplicationId) {
		return jobApplicationService.findJobApplicationById(jobApplicationId);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<JobApplicationDto>> updateJobApplication(
			@RequestBody JobApplication jobApplication, @RequestParam int jobApplicationId) {
		return jobApplicationService.updateJobApplication(jobApplication, jobApplicationId);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<JobApplicationDto>> deleteJobApplication(
			@RequestParam int jobApplicationId) {
		return jobApplicationService.deleteJobApplication(jobApplicationId);
	}

}
