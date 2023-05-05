package com.ty.Hired_JobPortal.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.Hired_JobPortal.Entity.Job;

public interface JobRepo extends JpaRepository<Job, Integer> {
	public Optional<List<Job>> findByJobName(String jobName);
	public Optional<List<Job>> findByCompanyName(String companyName);
	public Optional<List<Job>> findByJobLocation(String jobLocation);
}
