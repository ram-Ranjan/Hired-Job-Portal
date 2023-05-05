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

	public ResponseEntity<ResponseStructure<EmployerDto>> addEmployer(Employer employer) {
		ResponseStructure<EmployerDto> responseStructure = new ResponseStructure<>();
		Employer employer2 = employerDao.addEmployer(employer);

		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Employer added Successfully!!");
		employerDto.setEmployerId(employer2.getEmployerId());
		employerDto.setEmployerName(employer2.getEmployerName());
		employerDto.setEmployerContact(employer2.getEmployerContact());
		responseStructure.setData(employer2);
		return new ResponseEntity<ResponseStructure<EmployerDto>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<EmployerDto>> getEmployer(int employerId) {
		ResponseStructure<EmployerDto> responseStructure = new ResponseStructure<>();
		Employer existingEmployer = employerDao.findEmployerById(employerId);

		if (existingEmployer != null) {
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Employer Found!!");
			employerDto.setEmployerId(existingEmployer.getEmployerId());
			employerDto.setEmployerName(existingEmployer.getEmployerName());
			employerDto.setEmployerContact(existingEmployer.getEmployerContact());
			employerDto.setJob(existingEmployer.getJob());
			responseStructure.setData(employerDto);
			return new ResponseEntity<ResponseStructure<EmployerDto>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException("Failed to find the Employer!!");
		}
	}

	public ResponseEntity<ResponseStructure<EmployerDto>> updateEmployer(Employer updatedEmployer, int employeeId) {
		ResponseStructure<EmployerDto> responseStructure = new ResponseStructure<>();
		Employer existingEmployer = employerDao.findEmployerById(employeeId);

		if (existingEmployer != null) {
			updatedEmployer.setEmployerId(existingEmployer.getEmployerId());
			existingEmployer = employerDao.updateEmployer(updatedEmployer);
			employerDto.setEmployerId(existingEmployer.getEmployerId());
			employerDto.setEmployerName(existingEmployer.getEmployerName());
			employerDto.setEmployerContact(existingEmployer.getEmployerContact());

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Employer Updated!!");
			responseStructure.setData(employerDto);

			return new ResponseEntity<ResponseStructure<EmployerDto>>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Failed to Update Employer!!");
		}

	}

	public ResponseEntity<ResponseStructure<EmployerDto>> deleteEmployer(int employerId) {
		ResponseStructure<EmployerDto> responseStructure = new ResponseStructure<>();
		Employer employer = employerDao.deleteEmployerById(employerId);

		if (employer != null) {
			employerDto.setEmployerId(employer.getEmployerId());
			employerDto.setEmployerName(employer.getEmployerName());
			employerDto.setEmployerContact(employer.getEmployerContact());
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Employer Deleted!!");
			responseStructure.setData(employerDto);
			return new ResponseEntity<ResponseStructure<EmployerDto>>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Failed to delete Employer!!");
		}
	}

}
