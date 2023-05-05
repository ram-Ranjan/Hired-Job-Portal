package com.ty.Hired_JobPortal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.Hired_JobPortal.Config.ResponseStructure;
import com.ty.Hired_JobPortal.DAO.EmployerDao;
import com.ty.Hired_JobPortal.DAO.JobDao;
import com.ty.Hired_JobPortal.DTO.DtoConfig;
import com.ty.Hired_JobPortal.DTO.JobDto;
import com.ty.Hired_JobPortal.Entity.Employer;
import com.ty.Hired_JobPortal.Entity.Job;
import com.ty.Hired_JobPortal.Exception.IdNotFoundException;
import com.ty.Hired_JobPortal.Exception.LocationNotFoundException;
import com.ty.Hired_JobPortal.Exception.NameNotFoundException;

@Service
public class JobService {
	@Autowired
	private JobDao jobDao;
	@Autowired
	private JobDto jobDto;
	@Autowired
	private DtoConfig dtoConfig;


	@Autowired
	private EmployerDao employerDao;

	
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

	public ResponseEntity<ResponseStructure<JobDto>> findAllJobsByName(String jobName) {
		ResponseStructure<JobDto> responseStructure = new ResponseStructure<>();
		Job existingJob = (Job) jobDao.findAllJobsByName(jobName);
    jobDto = dtoConfig.setJobDtoAttributes(existingJob);

		if (existingJob != null) {
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Jobs Found!!");
		
			responseStructure.setData(existingJob);
			return new ResponseEntity<ResponseStructure<JobDto>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new NameNotFoundException("Failed to find any Job with the Name!!");
		}
	}

	public ResponseEntity<ResponseStructure<List<JobDto>>> findAllJobsByCompanyName(String companyName) {
		ResponseStructure<List<JobDto>> responseStructure = new ResponseStructure<>();
		List<Job> existingJob = jobDao.findAllJobsByCompanyName(companyName);

		if (existingJob != null) {
    jobDto = dtoConfig.setJobDtoAttributes(existingJob);
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Jobs Found!!");
	
			responseStructure.setData(existingJob);
			return new ResponseEntity<ResponseStructure<List<JobDto>>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new NameNotFoundException("Failed to find any Job with the CompanyName!!");
		}
	}

	public ResponseEntity<ResponseStructure<JobDto>> findAllJobsByJobLocation(String jobLocation) {
		ResponseStructure<JobDto> responseStructure = new ResponseStructure<>();
		List<Job> existingJob = jobDao.findAllJobsByJobLocation(jobLocation);

		if (existingJob != null) {
    jobDto = dtoConfig.setJobDtoAttributes(existingJob);
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Jobs Found!!");
	
			responseStructure.setData(existingJob);
			return new ResponseEntity<ResponseStructure<JobDto>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new LocationNotFoundException("Failed to find any Job with the Location");
		}
	}

}
