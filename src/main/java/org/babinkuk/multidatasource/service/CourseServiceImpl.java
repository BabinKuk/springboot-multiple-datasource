package org.babinkuk.multidatasource.service;

import java.util.List;
import java.util.Optional;

import org.babinkuk.multidatasource.entity.coursetracker.Course;
import org.babinkuk.multidatasource.repository.coursetracker.CourseRepository;
import org.babinkuk.multidatasource.repository.coursetracker.custom.CourseTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * implementation class for CourseService interface 
 */
@Service
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;
	private final CourseTrackerRepository courseTrackerRepository;
	
	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository, CourseTrackerRepository courseTrackerRepository) {
		this.courseRepository = courseRepository;
		this.courseTrackerRepository = courseTrackerRepository;
	}

	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Optional<Course> getCourseById(int id) {
		return courseRepository.findById(id);
	}

	@Override
	public List<String> getAllCourseTitles() {
		return courseTrackerRepository.getAllCourseTitles();
	}
}
