package com.ty.Hired_JobPortal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.Hired_JobPortal.Config.ResponseStructure;
import com.ty.Hired_JobPortal.DTO.EmployerDto;
import com.ty.Hired_JobPortal.Entity.Employer;
import com.ty.Hired_JobPortal.Service.EmployerService;

@RestController
@RequestMapping("/employer")
public class EmployerController {
	@Autowired
	private EmployerService employerService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<EmployerDto>> addEmployer(@RequestBody Employer employer){
		return employerService.addEmployer(employer);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<EmployerDto>> getEmployer(@RequestParam int employerId){
		return employerService.getEmployer(employerId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<EmployerDto>> updateEmployer(@RequestBody Employer employer, @RequestParam int employerId ){
		return employerService.updateEmployer(employer, employerId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<EmployerDto>> deleteEmployer(@RequestParam int employerId){
		return employerService.deleteEmployer(employerId);
	}
	
}
