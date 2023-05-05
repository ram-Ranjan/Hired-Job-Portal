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
import com.ty.Hired_JobPortal.DTO.JobDto;
import com.ty.Hired_JobPortal.Entity.Job;
import com.ty.Hired_JobPortal.Service.JobService;

@RestController
@RequestMapping("/job")
public class JobController {
	@Autowired
	private JobService jobService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<JobDto>> addJob(@RequestBody Job job,@RequestParam int jobId){
		return jobService.addJob(job, jobId);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<JobDto>> getJobById(@RequestParam int jobId){
		return jobService.getJob(jobId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<JobDto>> updateJobById(@RequestBody Job job,@RequestParam int jobId){
		return jobService.updateJob(job, jobId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<JobDto>> deleteJobById(@RequestParam int jobId){
		return jobService.deleteJob(jobId);
	}
}
