package com.ty.Hired_JobPortal.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@NotNull(message = "skillName shouldn't be null")
	private String skillName;
	@NotBlank(message = "skillDescription shouldn't be blank")
	@NotNull(message = "skillDescription shouldn't be null")
	private String skillDescription;
	
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "skill")
	private List<Job> job;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "applicantId")
	private Applicant applicant;
	
}
