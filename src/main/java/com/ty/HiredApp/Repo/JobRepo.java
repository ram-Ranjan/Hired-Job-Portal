package com.ty.HiredApp.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.Hired_JobPortal.Entity.Job;

public interface JobRepo extends JpaRepository<Job, Integer>{

}
