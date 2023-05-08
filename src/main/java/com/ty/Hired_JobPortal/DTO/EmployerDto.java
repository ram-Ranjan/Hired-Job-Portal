package com.ty.Hired_JobPortal.DTO;

import java.util.List;

import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import com.ty.Hired_JobPortal.Entity.Job;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class EmployerDto {
	private int employerId;
	private String employerName;
	private String employerEmail;
	private long employerContact;

	@OneToMany
	private List<Job> job;

}
