package com.ty.Hired_JobPortal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.Hired_JobPortal.Config.ResponseStructure;
import com.ty.Hired_JobPortal.DAO.EmployerDao;
import com.ty.Hired_JobPortal.DTO.EmployerDto;
import com.ty.Hired_JobPortal.Entity.Employer;
import com.ty.Hired_JobPortal.Exception.IdNotFoundException;

@Service
public class EmployerService {
	@Autowired
	private EmployerDao employerDao;
	
	@Autowired
	private EmployerDto employerDto;
	
	public ResponseEntity<ResponseStructure<EmployerDto>> saveEmployer(Employer employer){
		ResponseStructure<EmployerDto> responseStructure = new ResponseStructure<>();
		Employer employer2 = employerDao.saveEmployer(employer);
		
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Employer added Successfully!!");
		employerDto.setEmployerId(employer2.getEmployerId());
		employerDto.setEmployerName(employer2.getEmployerName());
		employerDto.setEmployerContact(employer2.getEmployerContact());
		responseStructure.setData(employer2);
		return new ResponseEntity<ResponseStructure<EmployerDto>>(responseStructure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<EmployerDto>> getEmployer(int employerId){
		ResponseStructure<EmployerDto> responseStructure = new ResponseStructure<>();
		Employer employer = employerDao.findEmployerById(employerId);
		
		if(employer != null) {
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Employer Found!!");
			employerDto.setEmployerId(employer.getEmployerId());
			employerDto.setEmployerName(employer.getEmployerName());
			employerDto.setEmployerContact(employer.getEmployerContact());
			employerDto.setJob(employer.getJob());
			responseStructure.setData(employerDto);
			return new ResponseEntity<ResponseStructure<EmployerDto>>(responseStructure, HttpStatus.FOUND); 
		}
		else {
			throw new IdNotFoundException("Failed to find the Employer!!");
		}
	}
	
}
