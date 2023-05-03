package com.ty.Hired_JobPortal.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.Hired_JobPortal.Entity.Applicant;

public interface ApplicantRepo extends JpaRepository<Applicant, Integer>{
	
	public Applicant findByApplicantEmail(String applicantEmail);
	

}
