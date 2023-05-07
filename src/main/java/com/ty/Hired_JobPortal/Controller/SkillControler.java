
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
import com.ty.Hired_JobPortal.DTO.SkillDto;
import com.ty.Hired_JobPortal.Entity.Skill;
import com.ty.Hired_JobPortal.Service.SkillService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/skill")
public class SkillControler {
	@Autowired
	private SkillService skillService;
	@ApiOperation(value = "Save Skill", notes = "API is used to save Skill ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully created"),
			@ApiResponse(code = 400, message = "Skill not found for the given Skill ID") })
	@PostMapping
	public ResponseEntity<ResponseStructure<SkillDto>> addSkill(@RequestBody Skill skill,@RequestParam int jobId) {
		return skillService.addSkill(skill,jobId);
	}
	@ApiOperation(value = "Get Skill", notes = "API is used to find Skill ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully Found"),
			@ApiResponse(code = 400, message = "Skill not found for the given Skill ID") })
	@GetMapping
	public ResponseEntity<ResponseStructure<SkillDto>> getSkill(@RequestParam int skillId) {
		return skillService.getSkill(skillId);
	}
	@ApiOperation(value = "Update Skill", notes = "API is used to Update Skill ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Succesfully Updated"),
			@ApiResponse(code = 400, message = "Skill not found for the given Skill ID") })
	@PutMapping
	public ResponseEntity<ResponseStructure<SkillDto>> updateSkill(@RequestBody Skill skill,
			@RequestParam int skillId) {
		return skillService.updateSkill(skill, skillId);
	}
	@ApiOperation(value = "Delete Skill", notes = "API is used to delete Skill ")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "succesfully deleted"),
			@ApiResponse(code = 400, message = "Skill not found for the given Skill ID") })
	@DeleteMapping
	public ResponseEntity<ResponseStructure<SkillDto>> deleteSkill(@RequestParam int skillId) {
		return skillService.deleteSkill(skillId);
	}
}
