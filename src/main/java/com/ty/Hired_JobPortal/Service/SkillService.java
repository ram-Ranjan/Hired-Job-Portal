package com.ty.Hired_JobPortal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.Hired_JobPortal.Config.ResponseStructure;
import com.ty.Hired_JobPortal.DAO.SkillDao;

import com.ty.Hired_JobPortal.DTO.DtoConfig;
import com.ty.Hired_JobPortal.DTO.SkillDto;
import com.ty.Hired_JobPortal.Entity.Skill;
import com.ty.Hired_JobPortal.Exception.IdNotFoundException;

@Service
public class SkillService {
	@Autowired
	private SkillDao skillDao;
	@Autowired
	private SkillDto skillDto;
	@Autowired
	private DtoConfig dtoConfig;
	
	public ResponseEntity<ResponseStructure<SkillDto>> addSkill(Skill skill){
		skill = skillDao.addSkill(skill);
		
		skillDto=dtoConfig.setSkillDtoAttributes(skill);
		ResponseStructure<SkillDto> responseStructure = new ResponseStructure<>();	
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Skill added Successfully!!");
		responseStructure.setData(skillDto);
		return new ResponseEntity<ResponseStructure<SkillDto>>(responseStructure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<SkillDto>> getSkill(int skillId){
		Skill existingSkill = skillDao.findSkillById(skillId);
		
		if(existingSkill != null) {
			skillDto=dtoConfig.setSkillDtoAttributes(existingSkill);
			ResponseStructure<SkillDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Skill Found!!");
			responseStructure.setData(skillDto);
			return new ResponseEntity<ResponseStructure<SkillDto>>(responseStructure,HttpStatus.CREATED);
		}
		else {
			throw new IdNotFoundException("Failed to find the Skill!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<SkillDto>> updateSkill(Skill updatedSkill, int skillId) {
		Skill existingSkill = skillDao.findSkillById(skillId);

		if (existingSkill != null) {
			skillDto=dtoConfig.setSkillDtoAttributes(existingSkill);
			updatedSkill.setSkillId(existingSkill.getSkillId());
			existingSkill = skillDao.updateSkill(updatedSkill);

			ResponseStructure<SkillDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Skill Updated!!");
			responseStructure.setData(skillDto);

			return new ResponseEntity<ResponseStructure<SkillDto>>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Failed to Update Skill!!");
		}

	}

	public ResponseEntity<ResponseStructure<SkillDto>> deleteSkill(int skillId) {
		Skill existingSkill = skillDao.deleteSkillById(skillId);

		if (existingSkill != null) {
			skillDto=dtoConfig.setSkillDtoAttributes(existingSkill);
			ResponseStructure<SkillDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Skill Deleted!!");
			responseStructure.setData(skillDto);
			return new ResponseEntity<ResponseStructure<SkillDto>>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Failed to delete Skill!!");
		}
	}

}
