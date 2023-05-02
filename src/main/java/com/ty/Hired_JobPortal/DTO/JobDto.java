package com.ty.Hired_JobPortal.DTO;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class JobDto {
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
