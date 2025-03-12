package org.babinkuk.multidatasource.controller;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.babinkuk.multidatasource.entity.coursetracker.Course;
import org.babinkuk.multidatasource.entity.jobportal.Skills;
import org.babinkuk.multidatasource.service.SkillsService;
import org.babinkuk.multidatasource.service.UserService;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/api/v1")
public class JobPortalController {

	private final SkillsService skillsService;
	private final UserService userService;
	
	@Autowired
	public JobPortalController(SkillsService skillsService, UserService userService) {
		this.skillsService = skillsService;
		this.userService = userService;
	}
	
	/**
	 * expose GET "/skills"
	 * get skill list
	 *
	 * @param 
	 * @return ResponseEntity
	 */
	@GetMapping("/skills")
	public ResponseEntity<Iterable<Skills>> getAllSkills(@RequestParam(required = false, name = "level") String level) {

		if (!StringUtils.isBlank(level)) {
			return ResponseEntity.of(Optional.ofNullable(skillsService.getSkillsByLevel(level)));
		}
		return ResponseEntity.of(Optional.ofNullable(skillsService.getAllSkills()));
	}
	
	/**
	 * expose GET "/skills/{skillId}"
	 * get specific skill
	 * 
	 * @param 
	 * @return ResponseEntity
	 */
	@GetMapping("/skills/{skillId}")
	public ResponseEntity<Skills> getSkill(@PathVariable int skillId) {
		return ResponseEntity.of(skillsService.getSkillById(skillId));
	}
	
	/**
	 * expose GET "/skills"
	 * get skill list
	 *
	 * @param 
	 * @return ResponseEntity
	 */
	@GetMapping("/skills/names")
	public ResponseEntity<Iterable<String>> getAllSkillNames(@RequestParam(required = false, name = "level") String level) {

		if (!StringUtils.isBlank(level)) {
			return ResponseEntity.of(Optional.ofNullable(skillsService.getSkillNamesByLevel(level)));
		}
		return ResponseEntity.of(Optional.ofNullable(skillsService.getSkillNames()));
	}

	/**
	 * expose GET "/email"
	 * get user email list
	 *
	 * @param 
	 * @return ResponseEntity
	 */
	@GetMapping("/users/email")
	public ResponseEntity<Iterable<String>> getAllUserEmails() {
		return ResponseEntity.of(Optional.ofNullable(userService.getAllUserEmails()));
	}
}
