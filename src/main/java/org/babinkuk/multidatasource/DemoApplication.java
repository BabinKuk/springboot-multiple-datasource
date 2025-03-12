package org.babinkuk.multidatasource;

import java.util.List;

import org.babinkuk.multidatasource.service.CourseService;
import org.babinkuk.multidatasource.service.SkillsService;
import org.babinkuk.multidatasource.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(CourseService courseService, UserService userService, SkillsService skillsService) {
		return runner -> {
			findData(courseService, userService, skillsService);
		};
	}

	private void findData(CourseService courseService, UserService userService, SkillsService skillsService) {
		findCourseDetails(courseService);
		findUsersDetails(userService);
		findSkillsDetails(skillsService);
	}

	private List<String> findCourseDetails(CourseService courseService) {
		return courseService.getAllCourseTitles();
	}
	
	private List<String> findUsersDetails(UserService userService) {
		return userService.getAllUserEmails();
	}
	
	private List<String> findSkillsDetails(SkillsService skillsService) {
		return skillsService.getSkillNamesByLevel("Advance");
	}
}
