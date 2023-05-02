package com.ty.Hired_JobPortal.DTO;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ty.Hired_JobPortal.Entity.Job;
import com.ty.Hired_JobPortal.Entity.Notification;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EmployerDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employerId;
	private String employerName;
	private long employerContact;
	
	@OneToMany
	private List<Job> job;
	
	@OneToMany
	private List<Notification> notification;
	
}
