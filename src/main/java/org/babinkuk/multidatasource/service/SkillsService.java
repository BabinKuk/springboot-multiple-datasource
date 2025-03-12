package org.babinkuk.multidatasource.service;

import java.util.List;
import java.util.Optional;

import org.babinkuk.multidatasource.entity.jobportal.Skills;

public interface SkillsService {
	
	List<Skills> getAllSkills();
	Optional<Skills> getSkillById(int id);
	List<Skills> getSkillsByLevel(String level);
	List<String> getSkillNamesByLevel(String level);
	List<String> getSkillNames();
}
