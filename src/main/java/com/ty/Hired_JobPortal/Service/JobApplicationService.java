package com.ty.Hired_JobPortal.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.Hired_JobPortal.Config.ResponseStructure;
import com.ty.Hired_JobPortal.DAO.ApplicantDao;
import com.ty.Hired_JobPortal.DAO.JobApplicationDao;
import com.ty.Hired_JobPortal.DAO.JobDao;
import com.ty.Hired_JobPortal.DAO.NotificationDao;
import com.ty.Hired_JobPortal.DTO.DtoConfig;
import com.ty.Hired_JobPortal.DTO.JobApplicationDto;
import com.ty.Hired_JobPortal.Entity.Applicant;
import com.ty.Hired_JobPortal.Entity.Job;
import com.ty.Hired_JobPortal.Entity.JobApplication;
import com.ty.Hired_JobPortal.Entity.Notification;
import com.ty.Hired_JobPortal.Exception.IdNotFoundForApplicantException;
import com.ty.Hired_JobPortal.Exception.IdNotFoundForEmployerException;
import com.ty.Hired_JobPortal.Exception.IdNotFoundForJobApplicationException;
import com.ty.Hired_JobPortal.Exception.IdNotFoundForJobException;

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
	@Autowired
	private NotificationDao notificationDao;

	public ResponseEntity<ResponseStructure<JobApplicationDto>> addJobApplication(JobApplication jobApplication,
			int applicantId, int jobId) {
		ResponseStructure<JobApplicationDto> responseStructure = new ResponseStructure<>();
		Applicant existingApplicant = applicantDao.findApplicantById(applicantId);

		if (existingApplicant != null) {
			Job existingJob = jobDao.findJobById(jobId);
			if (existingJob != null) {

				List<Applicant> applicants = existingJob.getApplicant();
				applicants.add(existingApplicant);
				jobDao.updateJob(existingJob,jobId);

				Notification notification = new Notification();
				notification.setNotificationMessage("Job Applied for " + existingJob.getJobName());
				notification.setNotificationTime(LocalDateTime.now());
				notification.setEmployer(existingJob.getEmployer());
				notification.setApplicant(existingApplicant);
				notificationDao.addNotification(notification);

				jobApplication.setJob(existingJob);
				jobApplication.setResume(existingApplicant.getResume());

				List<JobApplication> exapplications = existingApplicant.getJobApplication();
				exapplications.add(jobApplication);
				applicantDao.addApplicant(existingApplicant);
				jobApplication = jobApplicationDao.addJobApplication(jobApplication);
				jobApplicationDto = dtoConfig.setJobApplicationDtoAttributes(jobApplication);

				responseStructure.setStatus(HttpStatus.CREATED.value());
				responseStructure.setMessage("Job Application added Successfully!!");
				responseStructure.setData(jobApplicationDto);
				return new ResponseEntity<ResponseStructure<JobApplicationDto>>(responseStructure, HttpStatus.CREATED);
			} else
				throw new IdNotFoundForJobException("Applicant Id Doesn't exist");
		} else
			throw new IdNotFoundForApplicantException("Applicant Id Doesn't exist");
	}

	public ResponseEntity<ResponseStructure<JobApplicationDto>> findJobApplicationById(int jobApplicantionId) {
		JobApplication existingJobApplication = jobApplicationDao.findJobApplicationById(jobApplicantionId);

		if (existingJobApplication != null) {
			ResponseStructure<JobApplicationDto> responseStructure = new ResponseStructure<>();

			jobApplicationDto = dtoConfig.setJobApplicationDtoAttributes(existingJobApplication);
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Job Application Found!!");
			responseStructure.setData(jobApplicationDto);
			return new ResponseEntity<ResponseStructure<JobApplicationDto>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundForJobApplicationException("Failed to find the Job Application with given id!!");
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
			throw new IdNotFoundForJobApplicationException("Job Application doesn't  Exist with  given id");

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
			throw new IdNotFoundForJobApplicationException("Job Application doesn't  Exist with  given id");

	}

}
