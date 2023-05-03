package com.ty.Hired_JobPortal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.Hired_JobPortal.Config.ResponseStructure;
import com.ty.Hired_JobPortal.DAO.ApplicantDao;
import com.ty.Hired_JobPortal.DTO.ApplicantDto;
import com.ty.Hired_JobPortal.DTO.DtoConfig;
import com.ty.Hired_JobPortal.Entity.Applicant;
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
	
	public ResponseEntity<ResponseStructure<ApplicantDto>> saveApplicant(Applicant applicant){
		
		applicant = applicantDao.saveApplicant(applicant);
		applicantDto=dtoConfig.setApplicantDtoAttributes(applicant);
		
		ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Applicant added Successfully!!");
		responseStructure.setData(applicantDto);
		return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<ApplicantDto>> getApplicantById(int applicantId){
		Applicant applicant = applicantDao.findApplicantById(applicantId);
		
		if(applicant != null) {
			ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();
			
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Employer Found!!");
			responseStructure.setData(applicantDto);
			return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure, HttpStatus.FOUND); 
		}
		else {
			throw new IdNotFoundException("Failed to find the Applicant with given id!!"); 
		}
	}
	
	
	public ResponseEntity<ResponseStructure<ApplicantDto>> updateApplicant(Applicant updatedApplicant,int id)
	{
		
		Applicant existingApplicant = applicantDao.findApplicantById(id);
		
		if(existingApplicant !=null)
		{
			ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();
			
			updatedApplicant=applicantDao.updateApplicant(existingApplicant);
			applicantDto= dtoConfig.setApplicantDtoAttributes(updatedApplicant);
			
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Applicant has been updated");
			responseStructure.setData(applicantDto);
			return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure,HttpStatus.OK);
		}
		else
			throw new IdNotFoundException("Applicant doesn't  Exist with  given id");
		
	}
	public ResponseEntity<ResponseStructure<ApplicantDto>> deleteApplicant(int id){
		
		Applicant existingApplicant = applicantDao.findApplicantById(id);
		if(existingApplicant !=null)
		{
			ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();
			
			applicantDao.deleteApplicantById(id);
			applicantDto= dtoConfig.setApplicantDtoAttributes(existingApplicant);
			
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Applicant has been updated");
			responseStructure.setData(applicantDto);
			return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure,HttpStatus.OK);
		}
		else
			throw new IdNotFoundException("Applicant doesn't  Exist with  given id");
		
		
	}
	public ResponseEntity<ResponseStructure<ApplicantDto>> getApplicantByEmail(String applicantEmail){
		Applicant applicant = applicantDao.findByApplicantEmail(applicantEmail);
		
		if(applicant != null) {
			ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();
			
			
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Employer Found!!");
			responseStructure.setData(applicantDto);
			return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure, HttpStatus.FOUND); 
		}
		else {
			throw new ApplicantEmailNotFoundException("Failed to find the Applicant with given id!!"); 
		}
	}
	
}
