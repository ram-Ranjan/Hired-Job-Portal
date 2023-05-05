package com.ty.Hired_JobPortal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.Hired_JobPortal.Config.ResponseStructure;
import com.ty.Hired_JobPortal.DAO.EmployerDao;
import com.ty.Hired_JobPortal.DTO.DtoConfig;
import com.ty.Hired_JobPortal.DTO.EmployerDto;
import com.ty.Hired_JobPortal.Entity.Employer;
import com.ty.Hired_JobPortal.Exception.IdNotFoundException;

@Service
public class EmployerService {
	@Autowired
	private EmployerDao employerDao;
	@Autowired
	private EmployerDto employerDto;
	@Autowired
	private DtoConfig dtoConfig;

	public ResponseEntity<ResponseStructure<EmployerDto>> addEmployer(Employer employer) {

		employer = employerDao.addEmployer(employer);
		employerDto = dtoConfig.setEmployerDtoAttributes(employer);
		ResponseStructure<EmployerDto> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Employer added Successfully!!");
		responseStructure.setData(employerDto);
		return new ResponseEntity<ResponseStructure<EmployerDto>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<EmployerDto>> getEmployer(int employerId) {
		Employer existingEmployer = employerDao.findEmployerById(employerId);

		if (existingEmployer != null) {
			employerDto = dtoConfig.setEmployerDtoAttributes(existingEmployer);

			ResponseStructure<EmployerDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Employer Found!!");
			responseStructure.setData(employerDto);
			return new ResponseEntity<ResponseStructure<EmployerDto>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException("Failed to find the Employer!!");
		}
	}

	public ResponseEntity<ResponseStructure<EmployerDto>> updateEmployer(Employer updatedEmployer, int employeeId) {
		Employer existingEmployer = employerDao.findEmployerById(employeeId);

		if (existingEmployer != null) {
			updatedEmployer.setEmployerId(existingEmployer.getEmployerId());
			existingEmployer = employerDao.updateEmployer(updatedEmployer);
			employerDto = dtoConfig.setEmployerDtoAttributes(existingEmployer);

			ResponseStructure<EmployerDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Employer Updated!!");
			responseStructure.setData(employerDto);

			return new ResponseEntity<ResponseStructure<EmployerDto>>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Failed to Update Employer!!");
		}

	}

	public ResponseEntity<ResponseStructure<EmployerDto>> deleteEmployer(int employerId) {

		Employer existingEmployer = employerDao.deleteEmployerById(employerId);
		if (existingEmployer != null) {
			employerDto = dtoConfig.setEmployerDtoAttributes(existingEmployer);
			ResponseStructure<EmployerDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Employer Deleted!!");
			responseStructure.setData(employerDto);
			return new ResponseEntity<ResponseStructure<EmployerDto>>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Failed to delete Employer!!");
		}
	}

}
