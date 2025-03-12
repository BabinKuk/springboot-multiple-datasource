package org.babinkuk.multidatasource.service;

import java.util.List;
import java.util.Optional;

import org.babinkuk.multidatasource.entity.coursetracker.Course;

public interface CourseService {
	
	List<Course> getAllCourses();
	Optional<Course> getCourseById(int id);
	List<String> getAllCourseTitles();
}
