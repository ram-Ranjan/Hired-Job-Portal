package com.ty.Hired_JobPortal.Controller;

import javax.validation.Valid;

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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/employer")
public class EmployerController {
	@Autowired
	private EmployerService employerService;
	@ApiOperation(value = "Save Admin", notes = "API is used to save Admin ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully created"),
			@ApiResponse(code = 400, message = "Id not found for the given Admin ID") })
	@PostMapping
	public ResponseEntity<ResponseStructure<EmployerDto>> addEmployer(@Valid @RequestBody Employer employer) {
		return employerService.addEmployer(employer);
	}
	@ApiOperation(value = "Save Admin", notes = "API is used to save Admin ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully created"),
			@ApiResponse(code = 400, message = "Id not found for the given Admin ID") })
	@GetMapping
	public ResponseEntity<ResponseStructure<EmployerDto>> findEmployerById(@RequestParam int employerId) {
		return employerService.getEmployer(employerId);
	}
	@ApiOperation(value = "Save Admin", notes = "API is used to save Admin ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully created"),
			@ApiResponse(code = 400, message = "Id not found for the given Admin ID") })
	@PutMapping
	public ResponseEntity<ResponseStructure<EmployerDto>> updateEmployerById(@Valid @RequestBody Employer employer,
			@RequestParam int employerId) {
		return employerService.updateEmployer(employer, employerId);
	}
	@ApiOperation(value = "Save Admin", notes = "API is used to save Admin ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully created"),
			@ApiResponse(code = 400, message = "Id not found for the given Admin ID") })
	@DeleteMapping
	public ResponseEntity<ResponseStructure<EmployerDto>> deleteEmployerById(@RequestParam int employerId) {
		return employerService.deleteEmployer(employerId);
	}
	@ApiOperation(value = "Save Admin", notes = "API is used to save Admin ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully created"),
			@ApiResponse(code = 400, message = "Id not found for the given Admin ID") })
	@GetMapping("/email")
	public ResponseEntity<ResponseStructure<EmployerDto>> findByEmployerEmail(@RequestParam String employerEmail) {
		return employerService.findByEmployerEmail(employerEmail);
	}
	@GetMapping("/employerName")
	public ResponseEntity<ResponseStructure<EmployerDto>> findByEmployerName(@RequestParam String employerName) {
		return employerService.findByEmployerName(employerName);
	}

}
