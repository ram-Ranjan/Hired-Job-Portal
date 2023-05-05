package com.ty.Hired_JobPortal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.Hired_JobPortal.Config.ResponseStructure;
import com.ty.Hired_JobPortal.DAO.JobDao;
import com.ty.Hired_JobPortal.DTO.DtoConfig;
import com.ty.Hired_JobPortal.DTO.JobDto;
import com.ty.Hired_JobPortal.Entity.Job;
import com.ty.Hired_JobPortal.Exception.IdNotFoundException;

@Service
public class JobService {
	@Autowired
	private JobDao jobDao;
	@Autowired
	private JobDto jobDto;
	@Autowired
	private DtoConfig dtoConfig;

	public ResponseEntity<ResponseStructure<JobDto>> addJob(Job job) {
		job = jobDao.addJob(job);
		jobDto = dtoConfig.setJobDtoAttributes(job);

		ResponseStructure<JobDto> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Job added Successfully!!");
		responseStructure.setData(jobDto);
		return new ResponseEntity<ResponseStructure<JobDto>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<JobDto>> getJob(int jobId) {
		Job existingJob = jobDao.findJobById(jobId);

		if (existingJob != null) {
			jobDto = dtoConfig.setJobDtoAttributes(existingJob);

			ResponseStructure<JobDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Job Found!!");
			responseStructure.setData(jobDto);
			return new ResponseEntity<ResponseStructure<JobDto>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException("Failed to find Job!!");
		}
	}

	public ResponseEntity<ResponseStructure<JobDto>> updateJob(Job updatedJob, int jobId) {
		Job existingJob = jobDao.findJobById(jobId);

		if (existingJob != null) {
			updatedJob.setJobId(existingJob.getJobId());
			existingJob = jobDao.updateJob(updatedJob);
			jobDto = dtoConfig.setJobDtoAttributes(existingJob);

			ResponseStructure<JobDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Job Updated!!");
			responseStructure.setData(jobDto);

			return new ResponseEntity<ResponseStructure<JobDto>>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Failed to Update Job!!");
		}

	}

	public ResponseEntity<ResponseStructure<JobDto>> deleteJob(int employerId) {
		Job existingJob = jobDao.deleteJobById(employerId);
		if (existingJob != null) {
			jobDto = dtoConfig.setJobDtoAttributes(existingJob);

			ResponseStructure<JobDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Job Deleted!!");
			responseStructure.setData(jobDto);

			return new ResponseEntity<ResponseStructure<JobDto>>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Failed to delete Job!!");
		}
	}
}
