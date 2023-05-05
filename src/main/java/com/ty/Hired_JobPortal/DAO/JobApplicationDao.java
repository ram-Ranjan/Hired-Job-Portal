package com.ty.Hired_JobPortal.DAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.Hired_JobPortal.Entity.JobApplication;
import com.ty.Hired_JobPortal.Repo.JobApplicationRepo;

@Repository
public class JobApplicationDao {
	@Autowired
	private JobApplicationRepo jobApplicationRepo;

	public JobApplication addJobApplication(JobApplication jobApplication) {
		return jobApplicationRepo.save(jobApplication);
	}

	public JobApplication findJobApplicationById(int id) {
		Optional<JobApplication> optional = jobApplicationRepo.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}

	public JobApplication updateJobApplication(JobApplication jobApplication, int id) {
		Optional<JobApplication> optional = jobApplicationRepo.findById(id);
		if (optional.isPresent()) {
			jobApplication.setJobApplicationId(id);
			return jobApplicationRepo.save(jobApplication);
		} else
			return null;
	}

	public JobApplication deleteJobApplicationById(int id) {
		Optional<JobApplication> optional = jobApplicationRepo.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			JobApplication jobApplication = optional.get();
			jobApplicationRepo.delete(jobApplication);
			return jobApplication;
		}
	}
}
