package com.ty.Hired_JobPortal.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Skill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int skillId;
	@NotBlank(message = "skillName shouldn't be blank")
	@NotNull(message = "skillName shouln't be null")
	private String skillName;
	@NotBlank(message = "skillDescription shouldn't be blank")
	@NotNull(message = "skillDescription shouln't be null")
	private String skillDescription;
	@NotBlank(message = "levelRequired shouldn't be blank")
	@NotNull(message = "levelRequired shouln't be null")
	private String levelRequired;
	
	@ManyToOne
	private Job job;
	@ManyToOne
	private Applicant applicant;
	
}
