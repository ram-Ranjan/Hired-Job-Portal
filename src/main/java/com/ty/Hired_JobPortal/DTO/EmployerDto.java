package com.ty.Hired_JobPortal.DTO;

import java.util.List;

import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ty.Hired_JobPortal.Entity.Job;
import com.ty.Hired_JobPortal.Entity.Notification;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class EmployerDto {
	private int employerId;
	private String employerName;
	private String employerEmail;
	private long employerContactInfo;

	@OneToMany
	@JsonIgnore
	private List<Job> job;

	@OneToMany
	private List<Notification> notification;
}
