package com.ty.Hired_JobPortal.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.Hired_JobPortal.Config.ResponseStructure;
import com.ty.Hired_JobPortal.DAO.ApplicantDao;
import com.ty.Hired_JobPortal.DAO.JobDao;
import com.ty.Hired_JobPortal.DAO.SkillDao;
import com.ty.Hired_JobPortal.DTO.DtoConfig;
import com.ty.Hired_JobPortal.DTO.SkillDto;
import com.ty.Hired_JobPortal.Entity.Applicant;
import com.ty.Hired_JobPortal.Entity.Job;
import com.ty.Hired_JobPortal.Entity.Skill;
import com.ty.Hired_JobPortal.Exception.IdNotFoundException;

@Service
public class SkillService {
	@Autowired
	private SkillDao skillDao;
	@Autowired
	private SkillDto skillDto;
	@Autowired
	private JobDao jobDao;
	@Autowired
	private DtoConfig dtoConfig;
	@Autowired
	ApplicantDao applicantDao;

	/**
	 * 
	 *To be consumed by Employer entity  after while or after adding job to add skills for it
	 */
	public ResponseEntity<ResponseStructure<SkillDto>> addSkillbyEmployer(Skill skill, int jobId) {
		ResponseStructure<SkillDto> responseStructure = new ResponseStructure<>();
		Job existingJob = jobDao.findJobById(jobId);

		if (existingJob != null) {
			List<Job> jobs = new ArrayList<>();
			jobs.add(existingJob);
			List<Skill> skills=new ArrayList<>();
			skills.add(skill);
			existingJob.setSkill(skills);
			skill.setJob(jobs);
			skill = skillDao.addSkill(skill);	
			
			skillDto = dtoConfig.setSkillDtoAttributes(skill);
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Skill added Successfully!!");
			responseStructure.setData(skillDto);
			return new ResponseEntity<ResponseStructure<SkillDto>>(responseStructure, HttpStatus.CREATED);
		}
		else {
			throw new IdNotFoundException("Failed to find the Job with passed JobId!!");
		}
	}
	/**
	 * 
	 *To be consumed by Applicant entity  after creating applicant to add Applicant skills
	 */
	public ResponseEntity<ResponseStructure<SkillDto>> addSkillByApplicant(Skill skill, int applicantId) {
		ResponseStructure<SkillDto> responseStructure = new ResponseStructure<>();
		Applicant existingApplicant = applicantDao.findApplicantById(applicantId);
		
		if (existingApplicant != null) {
			skill.setApplicant(existingApplicant);
			skill = skillDao.addSkill(skill);	
			skillDto = dtoConfig.setSkillDtoAttributes(skill);
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Skill added Successfully!!");
			responseStructure.setData(skillDto);
			return new ResponseEntity<ResponseStructure<SkillDto>>(responseStructure, HttpStatus.CREATED);
		}
		else 
			throw new IdNotFoundException("Failed to find the Applicant with passed ApplicantId!!");
		
	}

	public ResponseEntity<ResponseStructure<SkillDto>> getSkill(int skillId) {
		Skill existingSkill = skillDao.findSkillById(skillId);

		if (existingSkill != null) {
			skillDto = dtoConfig.setSkillDtoAttributes(existingSkill);
			ResponseStructure<SkillDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Skill Found!!");
			responseStructure.setData(skillDto);
			return new ResponseEntity<ResponseStructure<SkillDto>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new IdNotFoundException("Failed to find the Skill!!");
		}
	}

	public ResponseEntity<ResponseStructure<SkillDto>> updateSkill(Skill updatedSkill, int skillId) {
		Skill existingSkill = skillDao.findSkillById(skillId);

		if (existingSkill != null) {
			skillDto = dtoConfig.setSkillDtoAttributes(existingSkill);
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
			skillDto = dtoConfig.setSkillDtoAttributes(existingSkill);
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
