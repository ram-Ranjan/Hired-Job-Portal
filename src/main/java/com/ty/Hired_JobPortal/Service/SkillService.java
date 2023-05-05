package com.ty.Hired_JobPortal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.Hired_JobPortal.Config.ResponseStructure;
import com.ty.Hired_JobPortal.DAO.SkillDao;
import com.ty.Hired_JobPortal.DTO.EmployerDto;
import com.ty.Hired_JobPortal.DTO.SkillDto;
import com.ty.Hired_JobPortal.Entity.Employer;
import com.ty.Hired_JobPortal.Entity.Skill;
import com.ty.Hired_JobPortal.Exception.IdNotFoundException;

@Service
public class SkillService {
	@Autowired
	private SkillDao skillDao;
	
	@Autowired
	private SkillDto skillDto;
	
	public ResponseEntity<ResponseStructure<SkillDto>> addSkill(Skill skill){
		ResponseStructure<SkillDto> responseStructure = new ResponseStructure<>();
		Skill skill2 = skillDao.addSkill(skill);
		
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Skill added Successfully!!");
		skillDto.setSkillId(skill2.getSkillId());
		skillDto.setSkillName(skill2.getSkillName());
		skillDto.setSkillDescription(skill2.getSkillDescription());
		skillDto.setLevelRequired(skill2.getLevelRequired());
		skillDto.setJob(skill2.getJob());
		skillDto.setApplicant(skill2.getApplicant());
		responseStructure.setData(skillDto);
		return new ResponseEntity<ResponseStructure<SkillDto>>(responseStructure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<SkillDto>> getSkill(int skillId){
		ResponseStructure<SkillDto> responseStructure = new ResponseStructure<>();
		Skill existingSkill = skillDao.findSkillById(skillId);
		
		if(existingSkill != null) {
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Skill Found!!");
			skillDto.setSkillId(existingSkill.getSkillId());
			skillDto.setSkillName(existingSkill.getSkillName());
			skillDto.setSkillDescription(existingSkill.getSkillDescription());
			skillDto.setLevelRequired(existingSkill.getLevelRequired());
			skillDto.setJob(existingSkill.getJob());
			skillDto.setApplicant(existingSkill.getApplicant());
			responseStructure.setData(skillDto);
			return new ResponseEntity<ResponseStructure<SkillDto>>(responseStructure,HttpStatus.CREATED);
		}
		else {
			throw new IdNotFoundException("Failed to find the Skill!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<SkillDto>> updateSkill(Skill updatedSkill, int skillId) {
		ResponseStructure<SkillDto> responseStructure = new ResponseStructure<>();
		Skill existingSkill = skillDao.findSkillById(skillId);

		if (existingSkill != null) {
			updatedSkill.setSkillId(existingSkill.getSkillId());
			existingSkill = skillDao.updateSkill(updatedSkill);
			skillDto.setSkillId(existingSkill.getSkillId());
			skillDto.setSkillName(existingSkill.getSkillName());
			skillDto.setSkillDescription(existingSkill.getSkillDescription());
			skillDto.setLevelRequired(existingSkill.getLevelRequired());
			skillDto.setJob(existingSkill.getJob());
			skillDto.setApplicant(existingSkill.getApplicant());

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Skill Updated!!");
			responseStructure.setData(skillDto);

			return new ResponseEntity<ResponseStructure<SkillDto>>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Failed to Update Skill!!");
		}

	}

	public ResponseEntity<ResponseStructure<SkillDto>> deleteSkill(int skillId) {
		ResponseStructure<SkillDto> responseStructure = new ResponseStructure<>();
		Skill existingSkill = skillDao.deleteSkillById(skillId);

		if (existingSkill != null) {
			skillDto.setSkillId(existingSkill.getSkillId());
			skillDto.setSkillName(existingSkill.getSkillName());
			skillDto.setSkillDescription(existingSkill.getSkillDescription());
			skillDto.setLevelRequired(existingSkill.getLevelRequired());
			skillDto.setJob(existingSkill.getJob());
			skillDto.setApplicant(existingSkill.getApplicant());

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Skill Deleted!!");
			responseStructure.setData(skillDto);
			return new ResponseEntity<ResponseStructure<SkillDto>>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Failed to delete Skill!!");
		}
	}

}
