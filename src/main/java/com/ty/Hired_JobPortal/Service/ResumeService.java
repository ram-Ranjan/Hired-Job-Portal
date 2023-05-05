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
import com.ty.Hired_JobPortal.Exception.IdNotFoundException;

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

	public ResponseEntity<ResponseStructure<ResumeDto>> addResume(Resume resume,int applicantId) {

		Applicant existingApplicant = applicantDao.findApplicantById(applicantId);
		if(existingApplicant!= null)
		{
		resume = resumeDao.addResume(resume);
		resumeDto = dtoConfig.setResumeDtoAttributes(resume);
		ResponseStructure<ResumeDto> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Resume added Successfully!!");
		responseStructure.setData(resumeDto);
		return new ResponseEntity<ResponseStructure<ResumeDto>>(responseStructure, HttpStatus.CREATED);
	}
		else 
			throw new IdNotFoundException("Applicant doesn't exist with given id");
			
	}

	public ResponseEntity<ResponseStructure<ResumeDto>> findResumeById(int id) {
		Resume existingResume = resumeDao.findResumeById(id);

		if (existingResume != null) {
			ResponseStructure<ResumeDto> responseStructure = new ResponseStructure<>();

			resumeDto = dtoConfig.setResumeDtoAttributes(existingResume);
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Resume Found!!");
			responseStructure.setData(resumeDto);
			return new ResponseEntity<ResponseStructure<ResumeDto>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException("Failed to find the Resume with given id!!");
		}
	}

	public ResponseEntity<ResponseStructure<ResumeDto>> updateResume(Resume updatedResume, int id) {

		Resume existingResume = resumeDao.findResumeById(id);

		if (existingResume != null) {
			ResponseStructure<ResumeDto> responseStructure = new ResponseStructure<>();

			updatedResume = resumeDao.updateResume(updatedResume, id);
			resumeDto = dtoConfig.setResumeDtoAttributes(updatedResume);

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Resume has been updated");
			responseStructure.setData(resumeDto);
			return new ResponseEntity<ResponseStructure<ResumeDto>>(responseStructure, HttpStatus.OK);
		} else
			throw new IdNotFoundException("Resume doesn't  Exist with  given id");

	}

	public ResponseEntity<ResponseStructure<ResumeDto>> deleteResumeById(int id) {

		Resume existingResume = resumeDao.findResumeById(id);
		if (existingResume != null) {
			ResponseStructure<ResumeDto> responseStructure = new ResponseStructure<>();

			resumeDao.deleteResumeById(id);
			resumeDto = dtoConfig.setResumeDtoAttributes(existingResume);

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Resume has been updated");
			responseStructure.setData(resumeDto);
			return new ResponseEntity<ResponseStructure<ResumeDto>>(responseStructure, HttpStatus.OK);
		} else
			throw new IdNotFoundException("Resume doesn't  Exist with  given id");

	}

}
