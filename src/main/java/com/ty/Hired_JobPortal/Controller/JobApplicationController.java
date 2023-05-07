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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/jobApplication")
public class JobApplicationController {
	@Autowired
	private JobApplicationService jobApplicationService;
	@ApiOperation(value = "Save JobApplication", notes = "API is used to save JobApplication ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully created"),
			@ApiResponse(code = 400, message = "Id not found for the given JobApplication ID") })
	@PostMapping
	public ResponseEntity<ResponseStructure<JobApplicationDto>> addJobApplication(
			@RequestBody JobApplication jobApplication,@RequestParam int applicantId,@RequestParam  int jobId) {
		return jobApplicationService.addJobApplication(jobApplication,applicantId,jobId);
	}
	@ApiOperation(value = "Get JobApplication", notes = "API is used to save JobApplication ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Succesfully Found"),
			@ApiResponse(code = 400, message = "Id not found for the given JobApplication ID") })
	@GetMapping
	public ResponseEntity<ResponseStructure<JobApplicationDto>> getJobApplication(@RequestParam int jobApplicationId) {
		return jobApplicationService.findJobApplicationById(jobApplicationId);
	}
	@ApiOperation(value = "Update JobApplication", notes = "API is used to save JobApplication ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully updated"),
			@ApiResponse(code = 400, message = "Id not found for the given JobApplication ID") })
	@PutMapping
	public ResponseEntity<ResponseStructure<JobApplicationDto>> updateJobApplication(
			@RequestBody JobApplication jobApplication, @RequestParam int jobApplicationId) {
		return jobApplicationService.updateJobApplication(jobApplication, jobApplicationId);
	}
	@ApiOperation(value = "Deleted JobApplication", notes = "API is used to save JobApplication ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Succesfully Deleted"),
			@ApiResponse(code = 400, message = "Id not found for the given JobApplication ID") })
	@DeleteMapping
	public ResponseEntity<ResponseStructure<JobApplicationDto>> deleteJobApplication(
			@RequestParam int jobApplicationId) {
		return jobApplicationService.deleteJobApplication(jobApplicationId);
	}

}
