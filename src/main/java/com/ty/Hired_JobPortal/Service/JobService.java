package com.ty.Hired_JobPortal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.Hired_JobPortal.Config.ResponseStructure;
import com.ty.Hired_JobPortal.DAO.JobDao;
import com.ty.Hired_JobPortal.DTO.JobDto;
import com.ty.Hired_JobPortal.Entity.Job;
import com.ty.Hired_JobPortal.Exception.IdNotFoundException;

@Service
public class JobService {
	@Autowired
	private JobDao jobDao;

	@Autowired
	private JobDto jobDto;

	public ResponseEntity<ResponseStructure<JobDto>> addJob(Job job) {
		ResponseStructure<JobDto> responseStructure = new ResponseStructure<>();
		Job job2 = jobDao.addJob(job);

		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Job added Successfully!!");
		jobDto.setJobId(job2.getJobId());
		jobDto.setJobName(job2.getJobName());
		jobDto.setCompanyName(job2.getCompanyName());
		jobDto.setJobDescription(job2.getJobDescription());
		jobDto.setJobLocation(job2.getJobLocation());
		jobDto.setSalary(job2.getSalary());
		jobDto.setDatePosted(job2.getDatePosted());
		jobDto.setFilled(job2.isFilled());
		jobDto.setJobCategory(job2.getJobCategory());
		responseStructure.setData(job2);
		return new ResponseEntity<ResponseStructure<JobDto>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<JobDto>> getJob(int jobId) {
		ResponseStructure<JobDto> responseStructure = new ResponseStructure<>();
		Job existingJob = jobDao.findJobById(jobId);

		if (existingJob != null) {
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Job Found!!");
			jobDto.setJobId(existingJob.getJobId());
			jobDto.setJobName(existingJob.getJobName());
			jobDto.setCompanyName(existingJob.getCompanyName());
			jobDto.setJobDescription(existingJob.getJobDescription());
			jobDto.setJobLocation(existingJob.getJobLocation());
			jobDto.setSalary(existingJob.getSalary());
			jobDto.setDatePosted(existingJob.getDatePosted());
			jobDto.setFilled(existingJob.isFilled());
			jobDto.setJobCategory(existingJob.getJobCategory());
			responseStructure.setData(existingJob);
			return new ResponseEntity<ResponseStructure<JobDto>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException("Failed to find Job!!");
		}
	}

	public ResponseEntity<ResponseStructure<JobDto>> updateJob(Job updatedJob, int jobId) {
		ResponseStructure<JobDto> responseStructure = new ResponseStructure<>();
		Job existingJob = jobDao.findJobById(jobId);

		if (existingJob != null) {
			updatedJob.setJobId(existingJob.getJobId());
			existingJob = jobDao.updateJob(updatedJob);
			jobDto.setJobId(existingJob.getJobId());
			jobDto.setJobName(existingJob.getJobName());
			jobDto.setCompanyName(existingJob.getCompanyName());
			jobDto.setJobDescription(existingJob.getJobDescription());
			jobDto.setJobLocation(existingJob.getJobLocation());
			jobDto.setSalary(existingJob.getSalary());
			jobDto.setDatePosted(existingJob.getDatePosted());
			jobDto.setFilled(existingJob.isFilled());
			jobDto.setJobCategory(existingJob.getJobCategory());

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Job Updated!!");
			responseStructure.setData(jobDto);

			return new ResponseEntity<ResponseStructure<JobDto>>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Failed to Update Job!!");
		}

	}

	public ResponseEntity<ResponseStructure<JobDto>> deleteJob(int employerId) {
		ResponseStructure<JobDto> responseStructure = new ResponseStructure<>();
		Job existingJob = jobDao.deleteJobById(employerId);
		if (existingJob != null) {
			jobDto.setJobId(existingJob.getJobId());
			jobDto.setJobName(existingJob.getJobName());
			jobDto.setCompanyName(existingJob.getCompanyName());
			jobDto.setJobDescription(existingJob.getJobDescription());
			jobDto.setJobLocation(existingJob.getJobLocation());
			jobDto.setSalary(existingJob.getSalary());
			jobDto.setDatePosted(existingJob.getDatePosted());
			jobDto.setFilled(existingJob.isFilled());
			jobDto.setJobCategory(existingJob.getJobCategory());

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Job Deleted!!");
			responseStructure.setData(jobDto);

			return new ResponseEntity<ResponseStructure<JobDto>>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Failed to delete Job!!");
		}
	}
}
