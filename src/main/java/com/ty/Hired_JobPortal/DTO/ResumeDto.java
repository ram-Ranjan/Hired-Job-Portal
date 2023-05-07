package com.ty.Hired_JobPortal.DTO;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import com.ty.Hired_JobPortal.Entity.JobApplication;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ResumeDto {
	private int resumeId;
	private String filePath;
	private LocalDateTime uploadDateTime;
	
	@OneToMany
	private List<JobApplication> jobApplicantion;

}
