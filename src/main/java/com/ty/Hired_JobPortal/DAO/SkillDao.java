package com.ty.Hired_JobPortal.DAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.Hired_JobPortal.Entity.Skill;
import com.ty.Hired_JobPortal.Repo.SkillRepo;

@Repository
public class SkillDao {
	@Autowired
	private SkillRepo skillRepo;

	public Skill addSkill(Skill skill) {
		return skillRepo.save(skill);
	}

	public Skill findSkillById(int id) {
		Optional<Skill> optional = skillRepo.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			return optional.get();
		}
	}

	public Skill updateSkill(Skill skill) {
		return skillRepo.save(skill);
	}

	public Skill deleteSkillById(int id) {
		Optional<Skill> optional = skillRepo.findById(id);
		if (optional.isEmpty()) {
			return null;
		} else {
			Skill skill = optional.get();
			skillRepo.delete(skill);
			return skill;
		}
	}
}
