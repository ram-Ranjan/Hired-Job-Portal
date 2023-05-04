package com.ty.Hired_JobPortal.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.Hired_JobPortal.Entity.Applicant;

public interface JobApplication extends JpaRepository<Applicant, Integer>{
	
	

}
