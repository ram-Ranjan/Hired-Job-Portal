package com.ty.Hired_JobPortal.Controller;

import java.util.List;

import javax.validation.Valid;

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
import com.ty.Hired_JobPortal.DTO.JobDto;
import com.ty.Hired_JobPortal.Entity.Job;
import com.ty.Hired_JobPortal.Service.JobService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/job")
public class JobController {
	@Autowired
	private JobService jobService;

	@ApiOperation(value = "Save Job", notes = "API is used to save Job ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully created"),
			@ApiResponse(code = 400, message = "Id not found for the given Job ID") })
	@PostMapping
	public ResponseEntity<ResponseStructure<JobDto>> addJob(@Valid @RequestBody Job job, @RequestParam int employerId) {
		return jobService.addJob(job, employerId);
	}

	@ApiOperation(value = "Get Job", notes = "API is used to save Job ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully found"),
			@ApiResponse(code = 400, message = "Id not found for the given Job ID") })
	@GetMapping
	public ResponseEntity<ResponseStructure<JobDto>> getJobById(@RequestParam int jobId) {
		return jobService.getJob(jobId);
	}

	@ApiOperation(value = "Update Job", notes = "API is used to save Job ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully updated"),
			@ApiResponse(code = 400, message = "Id not found for the given Job ID") })
	@PutMapping
	public ResponseEntity<ResponseStructure<JobDto>> updateJobById(@Valid @RequestBody Job job,
			@RequestParam int jobId) {
		return jobService.updateJob(job, jobId);
	}

	@ApiOperation(value = "Delete Job", notes = "API is used to save Job ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully deleted"),
			@ApiResponse(code = 400, message = "Id not found for the given Job ID") })
	@DeleteMapping
	public ResponseEntity<ResponseStructure<JobDto>> deleteJobById(@RequestParam int jobId) {
		return jobService.deleteJob(jobId);
	}

	@ApiOperation(value = "Get Job", notes = "API is used to find Job ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully found"),
			@ApiResponse(code = 400, message = "Id not found for the given Job ID") })
	@GetMapping("/jobName")
	public ResponseEntity<ResponseStructure<List<JobDto>>> getAllJobsByName(@Valid @RequestParam String jobName) {
		return jobService.findAllJobsByName(jobName);
	}

	@ApiOperation(value = "Get Job", notes = "API is used to find Job ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully found"),
			@ApiResponse(code = 400, message = "Job not found for the given Job Company") })
	@GetMapping("/companyName")
	public ResponseEntity<ResponseStructure<List<JobDto>>> getAllJobsByCompanyName(@RequestParam String companyName) {
		return jobService.findAllJobsByCompanyName(companyName);
	}

	@ApiOperation(value = "Get Job", notes = "API is used to find Job ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully found"),
			@ApiResponse(code = 400, message = "Job not found for the given Job Location") })
	@GetMapping("/jobLocation")
	public ResponseEntity<ResponseStructure<List<JobDto>>> getAllJobsByJobLocation(@RequestParam String jobLocation) {
		return jobService.findAllJobsByJobLocation(jobLocation);
	}

	@ApiOperation(value = "Get Job", notes = "API is used to find Job ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully found"),
			@ApiResponse(code = 400, message = "Job not found for the given Employer Id") })
	@GetMapping("/employerId")
	public ResponseEntity<ResponseStructure<List<JobDto>>> getAllJobsByEmployerName(@RequestParam int employerId) {
		return jobService.findAllJobsByEmployerId(employerId);
	}

}
