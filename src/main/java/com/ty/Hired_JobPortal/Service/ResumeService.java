package com.ty.Hired_JobPortal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.Hired_JobPortal.Config.ResponseStructure;
import com.ty.Hired_JobPortal.DAO.ApplicantDao;
import com.ty.Hired_JobPortal.DAO.ResumeDao;
import com.ty.Hired_JobPortal.DTO.DtoConfig;
import com.ty.Hired_JobPortal.DTO.ResumeDto;
import com.ty.Hired_JobPortal.Entity.Applicant;
import com.ty.Hired_JobPortal.Entity.Resume;
import com.ty.Hired_JobPortal.Exception.IdNotFoundForApplicantException;
import com.ty.Hired_JobPortal.Exception.IdNotFoundForResumeException;

@Service
public class ResumeService {
	@Autowired
	private ResumeDao resumeDao;
	@Autowired
	private ApplicantDao applicantDao;
	@Autowired
	private ResumeDto resumeDto;
	@Autowired
	private DtoConfig dtoConfig;

	public ResponseEntity<ResponseStructure<ResumeDto>> addResume(Resume resume, int applicantId) {
		ResponseStructure<ResumeDto> responseStructure = new ResponseStructure<>();
		Applicant existingApplicant = applicantDao.findApplicantById(applicantId);
		if (existingApplicant != null) {

			existingApplicant.setResume(resume);
			resume = resumeDao.addResume(resume);
			resumeDto = dtoConfig.setResumeDtoAttributes(resume);
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Resume added Successfully!!");
			responseStructure.setData(resumeDto);
			return new ResponseEntity<ResponseStructure<ResumeDto>>(responseStructure, HttpStatus.CREATED);
		} else
			throw new IdNotFoundForApplicantException("Applicant doesn't exist with given id");

	}

	public ResponseEntity<ResponseStructure<ResumeDto>> findResumeById(int resumeId) {
		Resume existingResume = resumeDao.findResumeById(resumeId);

		if (existingResume != null) {
			ResponseStructure<ResumeDto> responseStructure = new ResponseStructure<>();

			resumeDto = dtoConfig.setResumeDtoAttributes(existingResume);
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Resume Found!!");
			responseStructure.setData(resumeDto);
			return new ResponseEntity<ResponseStructure<ResumeDto>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundForResumeException("Failed to find the Resume with given id!!");
		}
	}

	public ResponseEntity<ResponseStructure<ResumeDto>> updateResume(Resume updatedResume, int resumeId) {

		Resume existingResume = resumeDao.findResumeById(resumeId);

		if (existingResume != null) {
			ResponseStructure<ResumeDto> responseStructure = new ResponseStructure<>();

			updatedResume = resumeDao.updateResume(updatedResume, resumeId);
			resumeDto = dtoConfig.setResumeDtoAttributes(updatedResume);

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Resume has been updated");
			responseStructure.setData(resumeDto);
			return new ResponseEntity<ResponseStructure<ResumeDto>>(responseStructure, HttpStatus.OK);
		} else
			throw new IdNotFoundForResumeException("Resume doesn't  Exist with  given id");

	}

	public ResponseEntity<ResponseStructure<ResumeDto>> deleteResumeById(int resumeId) {

		Resume existingResume = resumeDao.findResumeById(resumeId);
		if (existingResume != null) {
			ResponseStructure<ResumeDto> responseStructure = new ResponseStructure<>();

			resumeDao.deleteResumeById(resumeId);
			resumeDto = dtoConfig.setResumeDtoAttributes(existingResume);

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Resume has been updated");
			responseStructure.setData(resumeDto);
			return new ResponseEntity<ResponseStructure<ResumeDto>>(responseStructure, HttpStatus.OK);
		} else
			throw new IdNotFoundForResumeException("Resume doesn't  Exist with  given id");

	}

}
