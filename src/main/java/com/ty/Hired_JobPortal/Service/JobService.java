package com.ty.Hired_JobPortal.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.Hired_JobPortal.Config.ResponseStructure;
import com.ty.Hired_JobPortal.DAO.EmployerDao;
import com.ty.Hired_JobPortal.DAO.JobDao;
import com.ty.Hired_JobPortal.DAO.SkillDao;
import com.ty.Hired_JobPortal.DTO.DtoConfig;
import com.ty.Hired_JobPortal.DTO.JobDto;
import com.ty.Hired_JobPortal.Entity.Employer;
import com.ty.Hired_JobPortal.Entity.Job;
import com.ty.Hired_JobPortal.Entity.Skill;
import com.ty.Hired_JobPortal.Exception.IdNotFoundException;
import com.ty.Hired_JobPortal.Exception.NameNotFoundException;

@Service
public class JobService {
	@Autowired
	private JobDao jobDao;
	@Autowired
	private JobDto jobDto;
	@Autowired
	private DtoConfig dtoConfig;
	@Autowired
	private SkillDao skillDao;

	@Autowired
	private EmployerDao employerDao;

	public ResponseEntity<ResponseStructure<JobDto>> addJob(Job job, int employerId) {
		ResponseStructure<JobDto> responseStructure = new ResponseStructure<>();
		Employer existingEmployer = employerDao.findEmployerById(employerId);
		if (existingEmployer != null) {
			job = jobDao.addJob(job);
			job.setEmployer(existingEmployer);

			List<Job> jobs = existingEmployer.getJob();
			jobs.add(job);
			existingEmployer.setJob(jobs);
			employerDao.updateEmployer(existingEmployer);

			jobDto = dtoConfig.setJobDtoAttributes(job);
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Job added Successfully!!");
			responseStructure.setData(jobDto);
			return new ResponseEntity<ResponseStructure<JobDto>>(responseStructure, HttpStatus.CREATED);
		} else {
			throw new IdNotFoundException("Employer Not Found with given Id!!");
		}
	}

	public ResponseEntity<ResponseStructure<JobDto>> getJob(int jobId) {
		Job existingJob = jobDao.findJobById(jobId);

		if (existingJob != null) {
			jobDto = dtoConfig.setJobDtoAttributes(existingJob);
			jobDto.setEmployer(existingJob.getEmployer());

			ResponseStructure<JobDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Job Found!!");
			responseStructure.setData(jobDto);
			return new ResponseEntity<ResponseStructure<JobDto>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException("Failed to find Job!!");
		}
	}

	public ResponseEntity<ResponseStructure<JobDto>> updateJob(Job updatedJob, int jobId) {
		ResponseStructure<JobDto> responseStructure = new ResponseStructure<>();
		Job existingJob = jobDao.findJobById(jobId);

		if (existingJob != null) {
			updatedJob.setJobId(existingJob.getJobId());
			updatedJob.setEmployer(existingJob.getEmployer());
			existingJob = jobDao.updateJob(updatedJob);
			jobDto = dtoConfig.setJobDtoAttributes(existingJob);

			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Job Updated!!");
			responseStructure.setData(jobDto);

			return new ResponseEntity<ResponseStructure<JobDto>>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Failed to Update Job!!");
		}

	}

	public ResponseEntity<ResponseStructure<JobDto>> deleteJob(int employerId) {
		Job existingJob = jobDao.deleteJobById(employerId);

		if (existingJob != null) {
			jobDto = dtoConfig.setJobDtoAttributes(existingJob);

			ResponseStructure<JobDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Job Deleted!!");
			responseStructure.setData(jobDto);

			return new ResponseEntity<ResponseStructure<JobDto>>(responseStructure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Failed to delete Job!!");
		}
	}

	public ResponseEntity<ResponseStructure<List<JobDto>>> findAllJobsByName(String jobName) {
		ResponseStructure<List<JobDto>> responseStructure = new ResponseStructure<>();
		List<Job> existingJobs = jobDao.findAllJobsByName(jobName);
		List<JobDto> jobLists = new ArrayList<>();

		if (existingJobs != null) {
			for (Job job : existingJobs) {
				jobDto = dtoConfig.setJobDtoAttributes(job);
				JobDto jobDto = dtoConfig.setJobDtoAttributes(job);
				jobLists.add(jobDto);

			}
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Jobs Found!!");
			responseStructure.setData(jobLists);

			return new ResponseEntity<ResponseStructure<List<JobDto>>>(responseStructure, HttpStatus.FOUND);

		} else
			throw new NameNotFoundException("Failed to find any Job with the CompanyName!!");
	}

	public ResponseEntity<ResponseStructure<List<JobDto>>> findAllJobsByCompanyName(String companyName) {
		ResponseStructure<List<JobDto>> responseStructure = new ResponseStructure<>();
		List<Job> existingJobs = jobDao.findAllJobsByCompanyName(companyName);
		List<JobDto> jobLists = new ArrayList<>();

		if (existingJobs != null) {
			for (Job job : existingJobs) {
				JobDto jobDto = dtoConfig.setJobDtoAttributes(job);
				jobLists.add(jobDto);
			}
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Jobs Found!!");

			responseStructure.setData(jobLists);

			return new ResponseEntity<ResponseStructure<List<JobDto>>>(responseStructure, HttpStatus.FOUND);
		} else
			throw new NameNotFoundException("Failed to find any Job with the Company Name!!");
	}

	public ResponseEntity<ResponseStructure<List<JobDto>>> findAllJobsByJobLocation(String jobLocation) {
		ResponseStructure<List<JobDto>> responseStructure = new ResponseStructure<>();
		List<Job> existingJobs = jobDao.findAllJobsByJobLocation(jobLocation);
		List<JobDto> jobLists = new ArrayList<>();

		if (existingJobs != null) {
			for (Job job : existingJobs) {
				JobDto jobDto = dtoConfig.setJobDtoAttributes(job);
				jobLists.add(jobDto);
			}
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Jobs Found!!");
			responseStructure.setData(jobLists);
			return new ResponseEntity<ResponseStructure<List<JobDto>>>(responseStructure, HttpStatus.FOUND);
		} else {
			throw new NameNotFoundException("Failed to find any Job with the Job Location!!");
		}
	}

	public ResponseEntity<ResponseStructure<List<JobDto>>> findJobsBySkillId(int skillId) {

		Skill existingSkill = skillDao.findSkillById(skillId);
		ResponseStructure<List<JobDto>> responseStructure = new ResponseStructure<>();
		List<JobDto> jobLists = new ArrayList<>();

		if (existingSkill != null) {
			List<Job> existingJobs = existingSkill.getJobs();

			if (existingJobs != null) {
				for (Job job : existingJobs) {
					JobDto jobDto = dtoConfig.setJobDtoAttributes(job);
					jobLists.add(jobDto);
				}
				responseStructure.setStatus(HttpStatus.FOUND.value());
				responseStructure.setMessage("Jobs Found!!");
				responseStructure.setData(jobLists);
				return new ResponseEntity<ResponseStructure<List<JobDto>>>(responseStructure, HttpStatus.FOUND);
			} else
				throw new IdNotFoundException("Failed to find any Job!!");


		} else
			throw new NameNotFoundException("Failed to find any Skill with the passed SkillName!!");
	}

	public ResponseEntity<ResponseStructure<List<JobDto>>> findAllJobsByEmployerId(int employerId) {
		ResponseStructure<List<JobDto>> responseStructure = new ResponseStructure<>();
		Employer existingEmployer = employerDao.findEmployerById(employerId);
		List<JobDto> jobLists = new ArrayList<>();

		if (existingEmployer != null) {
			List<Job> existingJobs = existingEmployer.getJob();
			if (existingJobs != null) {
				for (Job job : existingJobs) {
					JobDto jobDto = dtoConfig.setJobDtoAttributes(job);
					jobLists.add(jobDto);
				}
				responseStructure.setStatus(HttpStatus.FOUND.value());
				responseStructure.setMessage("Jobs Found!!");
				responseStructure.setData(jobLists);
				return new ResponseEntity<ResponseStructure<List<JobDto>>>(responseStructure, HttpStatus.FOUND);

			} else
				throw new IdNotFoundException("Failed to find any Job !!");
		} else
			throw new NameNotFoundException("Failed to find any Job with the Employer Name!!");

	}

}
