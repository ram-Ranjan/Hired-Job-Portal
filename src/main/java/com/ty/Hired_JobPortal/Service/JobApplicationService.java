package com.ty.Hired_JobPortal.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.Hired_JobPortal.Config.ResponseStructure;
import com.ty.Hired_JobPortal.DAO.ApplicantDao;
import com.ty.Hired_JobPortal.DAO.JobApplicationDao;
import com.ty.Hired_JobPortal.DAO.JobDao;
import com.ty.Hired_JobPortal.DTO.DtoConfig;
import com.ty.Hired_JobPortal.DTO.JobApplicationDto;
import com.ty.Hired_JobPortal.Entity.Applicant;
import com.ty.Hired_JobPortal.Entity.Job;
import com.ty.Hired_JobPortal.Entity.JobApplication;
import com.ty.Hired_JobPortal.Exception.IdNotFoundException;

@Service
public class JobApplicationService {
	@Autowired
	private JobApplicationDao jobApplicationDao;

	@Autowired
	private JobApplicationDto jobApplicationDto;
	@Autowired
	private ApplicantDao applicantDao;
	@Autowired
	private DtoConfig dtoConfig;
	@Autowired
	private JobDao jobDao;

	public ResponseEntity<ResponseStructure<JobApplicationDto>> addJobApplication(JobApplication jobApplication,
			int applicantId, int jobId) {
		ResponseStructure<JobApplicationDto> responseStructure = new ResponseStructure<>();
		Applicant existingApplicant = applicantDao.findApplicantById(applicantId);
		
		if (existingApplicant != null) {
			Job existingJob = jobDao.findJobById(jobId);
			if (existingJob != null) {
				List<Applicant> applicants = new ArrayList<>();
				List<JobApplication> jobApplications = new ArrayList<>();
				jobApplications.add(jobApplication);
				existingApplicant.setJobApplication(jobApplications);
				
				applicants.add(existingApplicant);
				
				List<Job> jobs = new ArrayList<>();
				jobs.add(existingJob);
				jobApplication.setJob(existingJob);
				
				jobApplication = jobApplicationDao.addJobApplication(jobApplication);
				jobApplicationDto = dtoConfig.setJobApplicationDtoAttributes(jobApplication);

				responseStructure.setStatus(HttpStatus.CREATED.value());
				responseStructure.setMessage("Job Application added Successfully!!");
				responseStructure.setData(jobApplicationDto);
				return new ResponseEntity<ResponseStructure<JobApplicationDto>>(responseStructure, HttpStatus.CREATED);
			} else
				throw new IdNotFoundException("Applicant Id Doesn't exist");
		} else
			throw new IdNotFoundException("Applicant Id Doesn't exist");
	}

	public ResponseEntity<ResponseStructure<JobApplicationDto>> findJobApplicationById(int id) {
		JobApplication existingJobApplication = jobApplicationDao.findJobApplicationById(id);

		if (existingJobApplication != null) {
			ResponseStructure<JobApplicationDto> responseStructure = new ResponseStructure<>();

			jobApplicationDto = dtoConfig.setJobApplicationDtoAttributes(existingJobApplication);
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Job Application Found!!");
			responseStructure.setData(jobApplicationDto);
			return new ResponseEntity<ResponseStructure<JobApplicationDto>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException("Failed to find the Job Application with given id!!");
		}
	}

	public ResponseEntity<ResponseStructure<JobApplicationDto>> updateJobApplication(JobApplication updatedApplicant,
			int id) {

		JobApplication existingJobApplication = jobApplicationDao.findJobApplicationById(id);

		if (existingJobApplication != null) {
			ResponseStructure<JobApplicationDto> responseStructure = new ResponseStructure<>();

			updatedApplicant = jobApplicationDao.updateJobApplication(updatedApplicant, id);
			jobApplicationDto = dtoConfig.setJobApplicationDtoAttributes(updatedApplicant);

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Job Application has been updated");
			responseStructure.setData(jobApplicationDto);
			return new ResponseEntity<ResponseStructure<JobApplicationDto>>(responseStructure, HttpStatus.OK);
		} else
			throw new IdNotFoundException("Job Application doesn't  Exist with  given id");

	}

	public ResponseEntity<ResponseStructure<JobApplicationDto>> deleteJobApplication(int id) {

		JobApplication existingJobApplication = jobApplicationDao.findJobApplicationById(id);
		if (existingJobApplication != null) {
			ResponseStructure<JobApplicationDto> responseStructure = new ResponseStructure<>();

			jobApplicationDao.deleteJobApplicationById(id);
			jobApplicationDto = dtoConfig.setJobApplicationDtoAttributes(existingJobApplication);

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Job Application has been updated");
			responseStructure.setData(jobApplicationDto);
			return new ResponseEntity<ResponseStructure<JobApplicationDto>>(responseStructure, HttpStatus.OK);
		} else
			throw new IdNotFoundException("Job Application doesn't  Exist with  given id");

	}

}
