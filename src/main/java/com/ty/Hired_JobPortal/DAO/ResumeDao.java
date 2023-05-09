package com.ty.Hired_JobPortal.DAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.Hired_JobPortal.Entity.Resume;
import com.ty.Hired_JobPortal.Repo.ResumeRepo;

@Repository
public class ResumeDao {

	@Autowired
	private ResumeRepo resumeRepo;

	public Resume addResume(Resume resume) {
		return resumeRepo.save(resume);
	}

	public Resume findResumeById(int id) {
		Optional<Resume> optional = resumeRepo.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}

	public Resume updateResume(Resume resume, int id) {
		Optional<Resume> optional = resumeRepo.findById(id);
		if (optional.isPresent()) {
			resume.setResumeId(id);
			return resumeRepo.save(resume);
		} else
			return null;
	}

	public Resume deleteResumeById(int id) {
		Optional<Resume> optional = resumeRepo.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			Resume resume = optional.get();
			resumeRepo.delete(resume);
			return resume;
		}
	}
}
