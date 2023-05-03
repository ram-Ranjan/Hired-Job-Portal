package com.ty.Hired_JobPortal.DAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.Hired_JobPortal.Entity.Applicant;
import com.ty.Hired_JobPortal.Repo.ApplicantRepo;
@Repository
public class ApplicantDao {
	@Autowired
	private ApplicantRepo applicantRepo;

	public Applicant saveApplicant(Applicant applicant ) {
		return applicantRepo.save(applicant);
	}

	public Applicant findApplicantById(int id) {
		Optional<Applicant> optional = applicantRepo.findById(id);
		System.err.println(optional.isEmpty());
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}

	public Applicant updateApplicant(Applicant applicant) {
		return applicantRepo.save(applicant);
	}

	public Applicant deleteApplicantById(int id) {
		Optional<Applicant> optional = applicantRepo.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			Applicant applicant = optional.get();
			applicantRepo.delete(applicant);
			return applicant;
		}
	}
	public Applicant findByApplicantEmail(String applicantEmail)
	{
		return applicantRepo.findByApplicantEmail(applicantEmail);
	}
}
