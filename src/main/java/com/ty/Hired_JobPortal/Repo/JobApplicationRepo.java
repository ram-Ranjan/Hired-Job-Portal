package com.ty.Hired_JobPortal.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.Hired_JobPortal.Entity.JobApplication;

public interface JobApplicationRepo extends JpaRepository<JobApplication, Integer> {

}
