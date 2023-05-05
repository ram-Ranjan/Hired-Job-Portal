package com.ty.Hired_JobPortal.DAO;

import java.util.ArrayList;
import java.util.List;
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

	public List<Job> findAllJobsByName(String jobName) {
		Optional<List<Job>> optionalList = jobRepo.findByJobName(jobName);
		List<Job> jobs = new ArrayList<>();
		if (optionalList.isEmpty()) {
			return null;
		} else {
			for (Job job : optionalList.get()) {
				jobs.add(job);
			}
		}
		return jobs;
	}

	public List<Job> findAllJobsByCompanyName(String companyName) {
		Optional<List<Job>> optionalList = jobRepo.findByCompanyName(companyName);
		List<Job> jobs = new ArrayList<>();
		if (optionalList.isEmpty()) {
			return null;
		} else {
			for (Job job : optionalList.get()) {
				jobs.add(job);
			}
		}
		return jobs;
	}

	public List<Job> findAllJobsByJobLocation(String jobLocation) {
		Optional<List<Job>> optionalList = jobRepo.findByJobLocation(jobLocation);
		List<Job> jobs = new ArrayList<>();
		if (optionalList.isEmpty()) {
			return null;
		} else {
			for (Job job : optionalList.get()) {
				jobs.add(job);
			}
		}
		return jobs;
	}
}
