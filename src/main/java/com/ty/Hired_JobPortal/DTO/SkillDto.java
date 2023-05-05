package com.ty.Hired_JobPortal.DTO;

import org.springframework.stereotype.Component;

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

	@ManyToOne
	private Job job;
	@ManyToOne
	private Applicant applicant;

}
