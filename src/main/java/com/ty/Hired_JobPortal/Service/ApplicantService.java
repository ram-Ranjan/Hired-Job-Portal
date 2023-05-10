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
import com.ty.Hired_JobPortal.DAO.ResumeDao;
import com.ty.Hired_JobPortal.DTO.ApplicantDto;
import com.ty.Hired_JobPortal.DTO.DtoConfig;
import com.ty.Hired_JobPortal.Entity.Applicant;
import com.ty.Hired_JobPortal.Entity.Job;
import com.ty.Hired_JobPortal.Entity.JobApplication;
import com.ty.Hired_JobPortal.Entity.Resume;
import com.ty.Hired_JobPortal.Exception.EmailAlreadyExistingForApplicantException;
import com.ty.Hired_JobPortal.Exception.EmailNotFoundForApplicantException;
import com.ty.Hired_JobPortal.Exception.IdNotFoundForApplicantException;
import com.ty.Hired_JobPortal.Exception.IdNotFoundForJobException;

@Service
public class ApplicantService {
	@Autowired
	private ApplicantDao applicantDao;

	@Autowired
	private ApplicantDto applicantDto;

	@Autowired	
	private JobDao jobDao;
	@Autowired
	private DtoConfig dtoConfig;
	@Autowired
	private ResumeDao resumeDao;
	@Autowired
	private JobApplicationDao jobApplicationDao;

	public ResponseEntity<ResponseStructure<ApplicantDto>> addApplicant(Applicant applicant) {

		Applicant existingApplicant = applicantDao.findByApplicantEmail(applicant.getApplicantEmail());
		if (existingApplicant == null) {
			applicant = applicantDao.addApplicant(applicant);
			applicantDto = dtoConfig.setApplicantDtoAttributes(applicant);

			ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Applicant added Successfully!!");
			responseStructure.setData(applicantDto);
			return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure, HttpStatus.CREATED);
		} else
			throw new EmailAlreadyExistingForApplicantException("Applicant Email already used");
	}

	public ResponseEntity<ResponseStructure<ApplicantDto>> findApplicantById(int applicantId) {
		Applicant applicant = applicantDao.findApplicantById(applicantId);

		if (applicant != null) {
			ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();

			applicantDto = dtoConfig.setApplicantDtoAttributes(applicant);
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Applicant Found!!");
			responseStructure.setData(applicantDto);
			return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundForApplicantException("Failed to find the Applicant with given id!!");
		}
	}

	public ResponseEntity<ResponseStructure<ApplicantDto>> updateApplicant(Applicant updatedApplicant, int id) {

		Applicant existingApplicant = applicantDao.findApplicantById(id);

		if (existingApplicant != null) {
			ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();

			updatedApplicant = applicantDao.updateApplicant(updatedApplicant, id);
			applicantDto = dtoConfig.setApplicantDtoAttributes(updatedApplicant);

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Applicant has been updated");
			responseStructure.setData(applicantDto);
			return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure, HttpStatus.OK);
		} else
			throw new IdNotFoundForApplicantException("Applicant doesn't  Exist with  given id");

	}

	public ResponseEntity<ResponseStructure<ApplicantDto>> deleteApplicant(int id) {
		Applicant existingApplicant = applicantDao.findApplicantById(id);
		if (existingApplicant != null) {

			List<JobApplication> jobApplicationList = existingApplicant.getJobApplication();
			Resume resume = existingApplicant.getResume();

			existingApplicant.setResume(null);
			existingApplicant.setJobApplication(null);

			resumeDao.deleteResumeById(resume.getResumeId());
			for (JobApplication jobApplication : jobApplicationList) {
				jobApplicationDao.deleteJobApplicationById(jobApplication.getJobApplicationId());
			}
			ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();
			applicantDao.deleteApplicantById(id);
			applicantDto = dtoConfig.setApplicantDtoAttributes(existingApplicant);

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Applicant has been deleted");
			responseStructure.setData(applicantDto);
			return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure, HttpStatus.OK);
		} else
			throw new IdNotFoundForApplicantException("Applicant doesn't  Exist with  given id");

	}

	public ResponseEntity<ResponseStructure<ApplicantDto>> findApplicantByEmail(String applicantEmail) {
		Applicant existingApplicant = applicantDao.findByApplicantEmail(applicantEmail);

		if (existingApplicant != null) {
			ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();

			ApplicantDto applicantDto = dtoConfig.setApplicantDtoAttributes(existingApplicant);
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Applicant Found!!");
			responseStructure.setData(applicantDto);
			return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new EmailNotFoundForApplicantException("Failed to find the Applicant with given Email!!");
		}
	}

	public ResponseEntity<ResponseStructure<List<ApplicantDto>>> findApplicantByJobId(int jobId) {
		ResponseStructure<List<ApplicantDto>> responseStructure = new ResponseStructure<>();
		Job existingJob = jobDao.findJobById(jobId);
		List<ApplicantDto> applicantLists = new ArrayList<>();

		if (existingJob != null) {
			List<Applicant> applicants = existingJob.getApplicant();
			existingJob.setApplicant(applicants);
			for (Applicant applicant : applicants) {
				ApplicantDto applicantDto = dtoConfig.setApplicantDtoAttributes(applicant);
				applicantLists.add(applicantDto);
			}
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Jobs Found!!");
			responseStructure.setData(applicantLists);
			return new ResponseEntity<ResponseStructure<List<ApplicantDto>>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundForJobException("Failed to find any Job with the Job Id!!");
		}

	}
}
