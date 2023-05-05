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
import com.ty.Hired_JobPortal.DTO.ResumeDto;
import com.ty.Hired_JobPortal.Entity.Resume;
import com.ty.Hired_JobPortal.Service.ResumeService;

@RestController
@RequestMapping("/resume")
public class ResumeController {
	@Autowired
	private ResumeService resumeService;

	@PostMapping
	public ResponseEntity<ResponseStructure<ResumeDto>> addResume(@RequestBody Resume resume,@RequestParam int applicantId) {
		return resumeService.addResume(resume,applicantId);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<ResumeDto>> getJobApplication(@RequestParam int resumeId) {
		return resumeService.findResumeById(resumeId);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<ResumeDto>> updateJobApplication(@RequestBody Resume resume,
			@RequestParam int resumeId) {
		return resumeService.updateResume(resume, resumeId);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<ResumeDto>> deleteJobApplication(@RequestParam int resumeId) {
		return resumeService.deleteResumeById(resumeId);
	}

}
