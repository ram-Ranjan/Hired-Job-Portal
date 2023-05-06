package com.ty.Hired_JobPortal.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.Hired_JobPortal.Entity.Skill;

public interface SkillRepo extends JpaRepository<Skill, Integer>{
//	public Skill findBySkillName();
//	public Skill findBySkillDescription();
//	public Skill findBySkillLevel();
}
