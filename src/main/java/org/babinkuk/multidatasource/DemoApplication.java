package org.babinkuk.multidatasource;

import java.util.List;

import org.babinkuk.multidatasource.repository.CourseRepository;
import org.babinkuk.multidatasource.repository.JobPortalNamedRepository;
import org.babinkuk.multidatasource.repository.JobPortalRepository;
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
	public CommandLineRunner commandLineRunner(CourseRepository courseRepository, JobPortalRepository jobPortalRepository, JobPortalNamedRepository jobPortalNamedRepository) {
		return runner -> {
			findData(courseRepository, jobPortalRepository, jobPortalNamedRepository);
		};
	}

	private void findData(CourseRepository courseRepository, JobPortalRepository jobPortalRepository, JobPortalNamedRepository jobPortalNamedRepository) {
		// TODO Auto-generated method stub
		List<String> courses = findCourseDetails(courseRepository);
		List<String> users = findUsersDetails(jobPortalRepository);
		List<String> skills = findSkillsDetails(jobPortalNamedRepository);
	}

	private List<String> findCourseDetails(CourseRepository courseRepository) {
		return courseRepository.getAllCourses();
	}
	
	private List<String> findUsersDetails(JobPortalRepository jobPortalRepository) {
		return jobPortalRepository.getAllUsers();
	}
	
	private List<String> findSkillsDetails(JobPortalNamedRepository jobPortalNamedRepository) {
		return jobPortalNamedRepository.getAllSkills("Advance");
	}
}
