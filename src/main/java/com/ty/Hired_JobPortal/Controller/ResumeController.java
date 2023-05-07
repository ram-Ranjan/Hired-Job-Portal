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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/resume")
public class ResumeController {
	@Autowired
	private ResumeService resumeService;
	@ApiOperation(value = "Save Resume", notes = "API is used to save Resume ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully created"),
			@ApiResponse(code = 400, message = "Resume not found for the given Resume ID") })
	@PostMapping
	public ResponseEntity<ResponseStructure<ResumeDto>> addResume(@RequestBody Resume resume,@RequestParam int applicantId) {
		return resumeService.addResume(resume,applicantId);
	}
	@ApiOperation(value = "Get Resume", notes = "API is used to Get Resume ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully Found"),
			@ApiResponse(code = 400, message = "Id not found for the given Resume ID") })
	@GetMapping
	public ResponseEntity<ResponseStructure<ResumeDto>> getJobApplication(@RequestParam int resumeId) {
		return resumeService.findResumeById(resumeId);
	}
	@ApiOperation(value = "Update Resume", notes = "API is used to Update Resume ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully Updated"),
			@ApiResponse(code = 400, message = "Id not found for the given Resume ID") })
	@PutMapping
	public ResponseEntity<ResponseStructure<ResumeDto>> updateJobApplication(@RequestBody Resume resume,
			@RequestParam int resumeId) {
		return resumeService.updateResume(resume, resumeId);
	}
	@ApiOperation(value = "Delete Resume", notes = "API is used to delete Resume ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully deleted"),
			@ApiResponse(code = 400, message = "Resume not found for the given Resume ID") })
	@DeleteMapping
	public ResponseEntity<ResponseStructure<ResumeDto>> deleteJobApplication(@RequestParam int resumeId) {
		return resumeService.deleteResumeById(resumeId);
	}

}
