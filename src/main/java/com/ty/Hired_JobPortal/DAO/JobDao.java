package com.ty.Hired_JobPortal.DAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.Hired_JobPortal.Entity.Job;
import com.ty.Hired_JobPortal.Repo.JobRepo;

@Repository
public class JobDao {
	@Autowired
	private JobRepo jobRepo;

	public Job addJob(Job job) {
		return jobRepo.save(job);
	}

	public Job findJobById(int jobId) {
		Optional<Job> optional = jobRepo.findById(jobId);
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}

	public Job updateJob(Job job) {
		return jobRepo.save(job);
	}

	public Job deleteJobById(int jobId) {
		Optional<Job> optional = jobRepo.findById(jobId);
		if (optional.isEmpty()) {
			return null;
		} else {
			Job job = optional.get();
			jobRepo.delete(job);
			return job;
		}
	}
}
