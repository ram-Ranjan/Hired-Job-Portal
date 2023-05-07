package com.ty.Hired_JobPortal.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.Hired_JobPortal.Entity.Applicant;

public interface ApplicantRepo extends JpaRepository<Applicant, Integer> {

	public Optional<Applicant> findByApplicantEmail(String applicantEmail);
	

}
