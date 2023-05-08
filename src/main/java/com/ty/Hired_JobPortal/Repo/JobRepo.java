package com.ty.Hired_JobPortal.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ty.Hired_JobPortal.Entity.Job;

public interface JobRepo extends JpaRepository<Job, Integer> {
	@Query("Select j from Job j where j.jobName = :jobName")
	public Optional<List<Job>> findByJobName(@Param ("jobName")String jobName);
	@Query("Select j from Job j where j.jobName = :companyName")
	public Optional<List<Job>> findByCompanyName(@Param ("companyName")String companyName);
	@Query("Select j from Job j where j.jobName = :jobLocation")
	public Optional<List<Job>> findByJobLocation(@Param ("jobLocation")String jobLocation);
}
