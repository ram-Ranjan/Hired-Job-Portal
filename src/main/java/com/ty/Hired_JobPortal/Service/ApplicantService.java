package com.ty.Hired_JobPortal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.Hired_JobPortal.Config.ResponseStructure;
import com.ty.Hired_JobPortal.DAO.ApplicantDao;
import com.ty.Hired_JobPortal.DAO.JobApplicationDao;
import com.ty.Hired_JobPortal.DAO.ResumeDao;
import com.ty.Hired_JobPortal.DTO.ApplicantDto;
import com.ty.Hired_JobPortal.DTO.DtoConfig;
import com.ty.Hired_JobPortal.Entity.Applicant;
import com.ty.Hired_JobPortal.Entity.JobApplication;
import com.ty.Hired_JobPortal.Entity.Resume;
import com.ty.Hired_JobPortal.Exception.ApplicantAlreadyExistingWithEmail;
import com.ty.Hired_JobPortal.Exception.ApplicantEmailNotFoundException;
import com.ty.Hired_JobPortal.Exception.IdNotFoundException;

@Service
public class ApplicantService {
	@Autowired
	private ApplicantDao applicantDao;

	@Autowired
	private ApplicantDto applicantDto;

	@Autowired
	private DtoConfig dtoConfig;
	
	@Autowired
	ResumeDao resumeDao;
	@Autowired
	JobApplicationDao jobApplicationDao;

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
			throw new ApplicantAlreadyExistingWithEmail("Applicant Email already used");
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
			throw new IdNotFoundException("Failed to find the Applicant with given id!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<ApplicantDto>> findByApplicantEmail(String applicantEmail) {
		Applicant applicant = applicantDao.findByApplicantEmail(applicantEmail);

		if (applicant != null) {
			ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();

			applicantDto = dtoConfig.setApplicantDtoAttributes(applicant);
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Applicant Found!!");
			responseStructure.setData(applicantDto);
			return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new ApplicantEmailNotFoundException("Failed to find the Applicant with given Email!!");
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
			throw new IdNotFoundException("Applicant doesn't  Exist with  given id");

	}

	public ResponseEntity<ResponseStructure<ApplicantDto>> deleteApplicant(int id) {
		Applicant existingApplicant = applicantDao.findApplicantById(id);
		if (existingApplicant != null) {
			
			List<JobApplication> jobApplicationList= existingApplicant.getJobApplication(); 
			Resume resume = existingApplicant.getResume();
			
			existingApplicant.setResume(null);
			existingApplicant.setJobApplication(null);
			
			resumeDao.deleteResumeById(resume.getResumeId());
			for(JobApplication jobApplication:jobApplicationList)
			{
				jobApplicationDao.deleteJobApplicationById(jobApplication.getJobApplicationId());
			}
			ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();
			applicantDao.deleteApplicantById(id);
			applicantDto = dtoConfig.setApplicantDtoAttributes(existingApplicant);

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Applicant has been updated");
			responseStructure.setData(applicantDto);
			return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure, HttpStatus.OK);
		} else
			throw new IdNotFoundException("Applicant doesn't  Exist with  given id");

	}

	

}
