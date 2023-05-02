package com.ty.Hired_JobPortal.DTO;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class JobDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int jobId;
	private String jobName;
	private String jobDescription;
	private String companyName;
	private String jobLocation;
	private double salary;
	private LocalDate datePosted;
	private boolean isFilled;
	private String jobCategory;
	
}
