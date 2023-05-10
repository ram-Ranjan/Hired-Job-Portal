package com.ty.Hired_JobPortal.DTO;

import java.time.LocalDateTime;

import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import com.ty.Hired_JobPortal.Entity.Job;
import com.ty.Hired_JobPortal.Entity.Resume;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class JobApplicationDto {

	private int jobApplicationId;
	private LocalDateTime jobApplicationAppliedDate;
	private int noticePeriod;
	private String jobApplicationWorkExperience;

	@ManyToOne
	private Job job;

	@ManyToOne
	private Resume resume;

}
