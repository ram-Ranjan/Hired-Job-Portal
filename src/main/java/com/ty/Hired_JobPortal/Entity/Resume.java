package com.ty.Hired_JobPortal.Entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Resume {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int resumeId;
	private String filePath;
	private LocalDateTime uploadDate;
	
	@OneToOne
	private JobApplicantion jobApplicantion;
	@ManyToOne
	private Applicant applicant;
}
