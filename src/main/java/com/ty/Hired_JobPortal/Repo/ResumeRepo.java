package com.ty.Hired_JobPortal.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.Hired_JobPortal.Entity.Resume;

public interface ResumeRepo extends JpaRepository<Resume, Integer> {

}
