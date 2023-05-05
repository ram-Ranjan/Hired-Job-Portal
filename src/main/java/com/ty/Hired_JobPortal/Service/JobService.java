package com.ty.Hired_JobPortal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.Hired_JobPortal.Config.ResponseStructure;
import com.ty.Hired_JobPortal.DAO.EmployerDao;
import com.ty.Hired_JobPortal.DAO.JobDao;
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
	private EmployerDao employerDao;

	public ResponseEntity<ResponseStructure<JobDto>> addJob(Job job, int employerId) {
		ResponseStructure<JobDto> responseStructure = new ResponseStructure<>();
		Employer existingEmployer = employerDao.findEmployerById(employerId);
		if (existingEmployer != null) {
			Job job2 = jobDao.addJob(job);
			job.setEmployer(existingEmployer);
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
		} else {
			throw new IdNotFoundException("Failed to find Employer");
		}
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

	public ResponseEntity<ResponseStructure<JobDto>> deleteJob(int jobId) {
		ResponseStructure<JobDto> responseStructure = new ResponseStructure<>();
		Job existingJob = jobDao.deleteJobById(jobId);
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

	public ResponseEntity<ResponseStructure<JobDto>> findAllJobsByName(String jobName) {
		ResponseStructure<JobDto> responseStructure = new ResponseStructure<>();
		Job existingJob = (Job) jobDao.findAllJobsByName(jobName);

		if (existingJob != null) {
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Jobs Found!!");
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
			throw new NameNotFoundException("Failed to find any Job with the Name!!");
		}
	}

	public ResponseEntity<ResponseStructure<List<JobDto>>> findAllJobsByCompanyName(String companyName) {
		ResponseStructure<List<JobDto>> responseStructure = new ResponseStructure<>();
		List<Job> existingJob = jobDao.findAllJobsByCompanyName(companyName);

		if (existingJob != null) {
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Jobs Found!!");
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
			return new ResponseEntity<ResponseStructure<List<JobDto>>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new NameNotFoundException("Failed to find any Job with the CompanyName!!");
		}
	}

	public ResponseEntity<ResponseStructure<JobDto>> findAllJobsByJobLocation(String jobLocation) {
		ResponseStructure<JobDto> responseStructure = new ResponseStructure<>();
		List<Job> existingJob = jobDao.findAllJobsByJobLocation(jobLocation);

		if (existingJob != null) {
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Jobs Found!!");
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
			throw new LocationNotFoundException("Failed to find any Job with the Location");
		}
	}

}
