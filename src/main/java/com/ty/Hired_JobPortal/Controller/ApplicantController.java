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
import com.ty.Hired_JobPortal.DTO.ApplicantDto;
import com.ty.Hired_JobPortal.Entity.Applicant;
import com.ty.Hired_JobPortal.Service.ApplicantService;

@RestController
@RequestMapping("/applicant")
public class ApplicantController {

	@Autowired
	ApplicantService applicantService;

	@PostMapping
	public ResponseEntity<ResponseStructure<ApplicantDto>> addApplicant(@RequestBody Applicant applicant) {
		return applicantService.addApplicant(applicant);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<ApplicantDto>> findApplicantById(@RequestParam int applicantId) {
		return applicantService.findApplicantById(applicantId);
	}

	@GetMapping("/email")
	public ResponseEntity<ResponseStructure<ApplicantDto>> findByApplicantEmail(@RequestParam String applicantEmail) {
		return applicantService.findByApplicantEmail(applicantEmail);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<ApplicantDto>> updateApplicant(@RequestBody Applicant applicant,
			int applicantId) {
		return applicantService.updateApplicant(applicant, applicantId);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<ApplicantDto>> deleteApplicantById(@RequestParam int applicantId) {
		return applicantService.deleteApplicant(applicantId);
	}

}
