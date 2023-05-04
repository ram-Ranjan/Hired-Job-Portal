package com.ty.Hired_JobPortal.Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Applicant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int applicantId;
	private String applicantFirstName;
	private String applicantLastName;
	private String applicantEmail;
	private String applicantPassword;
	private long applicantContact;
	private String applicantAddress;

	@OneToMany
	private List<Skill> skill;
	@OneToOne
	private Resume resume;
	@OneToMany
	private List<JobApplication> jobApplication;
	@OneToMany
	private List<Notification> notification;
	@ManyToMany
	private List<Job> job;
}
