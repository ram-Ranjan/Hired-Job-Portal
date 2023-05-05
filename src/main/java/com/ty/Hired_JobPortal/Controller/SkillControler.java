
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

@RestController
@RequestMapping("/skill")
public class SkillControler {
	@Autowired
	private SkillService skillService;

	@PostMapping
	public ResponseEntity<ResponseStructure<SkillDto>> addSkill(@RequestBody Skill skill) {
		return skillService.addSkill(skill);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<SkillDto>> getSkill(@RequestParam int skillId) {
		return skillService.getSkill(skillId);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<SkillDto>> updateSkill(@RequestBody Skill skill,
			@RequestParam int skillId) {
		return skillService.updateSkill(skill, skillId);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<SkillDto>> deleteSkill(@RequestParam int skillId) {
		return skillService.deleteSkill(skillId);
	}
}
