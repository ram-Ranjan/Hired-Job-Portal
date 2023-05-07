package com.ty.Hired_JobPortal.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.Hired_JobPortal.Entity.Employer;

public interface EmployerRepo extends JpaRepository<Employer, Integer> {
	public Optional<Employer> findByEmployerEmail(String employerEmail);

	public Optional<Employer> findByEmployerName(String employerName);

}
