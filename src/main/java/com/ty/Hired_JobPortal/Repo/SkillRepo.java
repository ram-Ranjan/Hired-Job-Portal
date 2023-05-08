package com.ty.Hired_JobPortal.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.Hired_JobPortal.Entity.Skill;

public interface SkillRepo extends JpaRepository<Skill, Integer>{
	
	public Optional<Skill> findBySkillName(String skillName);

}
