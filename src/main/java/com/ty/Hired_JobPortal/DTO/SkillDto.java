package com.ty.Hired_JobPortal.DTO;

import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import com.ty.Hired_JobPortal.Entity.Applicant;
import com.ty.Hired_JobPortal.Entity.Job;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class SkillDto {
	private int skillId;
	private String skillName;
	private String skillDescription;
	private String levelRequired;

	@ManyToMany
	private List<Job> job;
	
	@ManyToOne
	private Applicant applicant;

}
