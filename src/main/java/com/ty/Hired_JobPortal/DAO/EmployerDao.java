package com.ty.Hired_JobPortal.DAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.Hired_JobPortal.Entity.Employer;
import com.ty.Hired_JobPortal.Repo.EmployerRepo;

@Repository
public class EmployerDao {
	@Autowired
	private EmployerRepo employerRepo;

	public Employer addEmployer(Employer employer) {
		return employerRepo.save(employer);
	}

	public Employer findEmployerById(int id) {
		Optional<Employer> optional = employerRepo.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}

	public Employer updateEmployer(Employer employer) {
		return employerRepo.save(employer);
	}

	public Employer deleteEmployerById(int id) {
		Optional<Employer> optional = employerRepo.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			Employer employer = optional.get();
			employerRepo.delete(employer);
			return employer;
		}
	}
}
