package com.ty.Hired_JobPortal.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.Hired_JobPortal.Entity.Job;

public interface JobRepo extends JpaRepository<Job, Integer> {
	@Query("Select j from Job j where j.jobName =?1")
	public Optional<List<Job>> findByJobName(String jobName);
	@Query("Select j from Job j where j.companyName =?1")
	public Optional<List<Job>> findByCompanyName(String companyName);
	@Query("Select j from Job j where j.jobLocation =?1")
	public Optional<List<Job>> findByJobLocation(String jobLocation);
}
