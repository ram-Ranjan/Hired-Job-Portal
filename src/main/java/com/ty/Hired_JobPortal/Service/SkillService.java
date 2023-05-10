package com.ty.Hired_JobPortal.Service;

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
import com.ty.Hired_JobPortal.Exception.IdNotFoundForApplicantException;
import com.ty.Hired_JobPortal.Exception.IdNotFoundForJobException;
import com.ty.Hired_JobPortal.Exception.IdNotFoundForSkillException;

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
	 * To be consumed by Employer entity after while or after adding job to add
	 * skills for it
	 */
	public ResponseEntity<ResponseStructure<SkillDto>> addSkillbyEmployer(String[] skills, int jobId) {
		ResponseStructure<SkillDto> responseStructure = new ResponseStructure<>();
		Job existingJob = jobDao.findJobById(jobId);
		if (existingJob != null) {
			for(String Skill : skills)
			{
				Skill existingSkill =skillDao.findbySkillName(Skill);
				if(existingSkill==null)
				{
					Skill newSkill = new Skill();
					newSkill.setSkillName(Skill);
					newSkill=skillDao.addSkill(newSkill);
					
					existingJob.getSkill().add(newSkill);
				}
				else {
					existingJob.getSkill().add(existingSkill);
				}
			}
			existingJob=jobDao.updateJob(existingJob,existingJob.getJobId());


			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Skill added Successfully!!");
			responseStructure.setData(existingJob.getSkill());
			return new ResponseEntity<ResponseStructure<SkillDto>>(responseStructure, HttpStatus.CREATED);
		}
		else {
			throw new IdNotFoundForJobException("Failed to find the Job with passed JobId!!");

		}
	}

	/**
	 * 
	 * To be consumed by Applicant entity after creating applicant to add Applicant
	 * skills
	 */
	public ResponseEntity<ResponseStructure<SkillDto>> addSkillByApplicant(String[] skills, int applicantId) {
		ResponseStructure<SkillDto> responseStructure = new ResponseStructure<>();
		Applicant existingApplicant = applicantDao.findApplicantById(applicantId);
		if (existingApplicant != null) {
			for (String skill : skills) {
				Skill exSkill = skillDao.findbySkillName(skill);
				if (exSkill == null) {
					Skill newSkill = new Skill();
					newSkill.setSkillName(skill);
					newSkill = skillDao.addSkill(newSkill);
					existingApplicant.getSkill().add(newSkill);
				} else {
					existingApplicant.getSkill().add(exSkill);
				}
			}
			existingApplicant = applicantDao.updateApplicant(existingApplicant, existingApplicant.getApplicantId());
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Skill added Successfully!!");
			responseStructure.setData(existingApplicant.getSkill());
			return new ResponseEntity<ResponseStructure<SkillDto>>(responseStructure, HttpStatus.CREATED);
		}
		else 
			throw new IdNotFoundForApplicantException("Failed to find the Applicant with passed ApplicantId!!");

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
			throw new IdNotFoundForSkillException("Failed to find the Skill!!");
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
			throw new IdNotFoundForSkillException("Failed to Update Skill!!");
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
			throw new IdNotFoundForSkillException("Failed to delete Skill!!");
		}
	}

}
