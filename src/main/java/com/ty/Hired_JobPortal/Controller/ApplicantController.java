package com.ty.Hired_JobPortal.Controller;

import java.util.List;

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
import com.ty.Hired_JobPortal.DTO.ApplicantDto;
import com.ty.Hired_JobPortal.Entity.Applicant;
import com.ty.Hired_JobPortal.Service.ApplicantService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/applicant")
public class ApplicantController {

	@Autowired
	ApplicantService applicantService;

	@ApiOperation(value = "Save Applicant", notes = "API is used to save Applicant ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully created"),
			@ApiResponse(code = 400, message = "Id not found for the given Applicant ID") })
	@PostMapping
	public ResponseEntity<ResponseStructure<ApplicantDto>> addApplicant(@Valid @RequestBody Applicant applicant) {
		return applicantService.addApplicant(applicant);
	}

	@ApiOperation(value = "Find  Applicant by Id", notes = "API is used to save Applicant ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully created"),
			@ApiResponse(code = 400, message = "Id not found for the given Applicant ID") })
	@GetMapping
	public ResponseEntity<ResponseStructure<ApplicantDto>> findApplicantById(@RequestParam int applicantId) {
		return applicantService.findApplicantById(applicantId);
	}

	@ApiOperation(value = "Update Applicant", notes = "API is used to update Applicant ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully Updated"),
			@ApiResponse(code = 400, message = "Id not found for the given Applicant ID") })
	@PutMapping
	public ResponseEntity<ResponseStructure<ApplicantDto>> updateApplicant(@Valid @RequestBody Applicant applicant,
			int applicantId) {
		return applicantService.updateApplicant(applicant, applicantId);
	}

	@ApiOperation(value = "Delete Applicant", notes = "API is used to delete Applicant ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully deleted"),
			@ApiResponse(code = 400, message = "Id not found for the given Applicant ID") })
	@DeleteMapping
	public ResponseEntity<ResponseStructure<ApplicantDto>> deleteApplicantById(@RequestParam int applicantId) {
		return applicantService.deleteApplicant(applicantId);
	}

	@ApiOperation(value = "Find Applicant by email", notes = "API is used to save Applicant ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully found"),
			@ApiResponse(code = 400, message = "Id not found for the given Applicant ID") })
	@GetMapping("/email")
	public ResponseEntity<ResponseStructure<ApplicantDto>> findByApplicantEmail(@RequestParam String applicantEmail) {
		return applicantService.findApplicantByEmail(applicantEmail);
	}
	
	@ApiOperation(value = "Find Applicant by Job", notes = "API is used to save Applicant ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully found"),
			@ApiResponse(code = 400, message = "Id not found for the given Applicant ID") })
	@GetMapping("/jobId")
	public ResponseEntity<ResponseStructure<List<ApplicantDto>>> findByApplicantJobId(@RequestParam int jobId) {
		return applicantService.findApplicantByJobId(jobId);
	}

}
