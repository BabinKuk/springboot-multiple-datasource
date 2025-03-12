package org.babinkuk.multidatasource.service;

import java.util.List;
import java.util.Optional;

import org.babinkuk.multidatasource.entity.jobportal.Skills;
import org.babinkuk.multidatasource.repository.jobportal.SkillsRepository;
import org.babinkuk.multidatasource.repository.jobportal.custom.JobPortalNamedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * implementation class for SkillsService interface 
 */
@Service
public class SkillsServiceImpl implements SkillsService {

	private final SkillsRepository skillsRepository;
	private final JobPortalNamedRepository jobPortalNamedRepository;
	
	@Autowired
	public SkillsServiceImpl(
			SkillsRepository skillsRepository,
			JobPortalNamedRepository jobPortalNamedRepository) {
		this.skillsRepository = skillsRepository;
		this.jobPortalNamedRepository = jobPortalNamedRepository;
	}

	@Override
	public List<Skills> getAllSkills() {
		return skillsRepository.findAll();
	}

	@Override
	public Optional<Skills> getSkillById(int id) {
		return skillsRepository.findById(id);
	}
	
	@Override
	public List<Skills> getSkillsByLevel(String level) {
		return skillsRepository.findByLevel(level);
	}

	@Override
	public List<String> getSkillNamesByLevel(String level) {
		return jobPortalNamedRepository.getAllSkillNames(level);
	}

	@Override
	public List<String> getSkillNames() {
		return getAllSkills().stream().map(Skills::getName).toList();
	}
}
