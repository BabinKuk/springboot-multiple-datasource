package org.babinkuk.multidatasource.controller;

import java.util.Optional;

import org.babinkuk.multidatasource.entity.coursetracker.Course;
import org.babinkuk.multidatasource.service.CourseServiceImpl;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/courses")
public class CourseController {

	private final CourseServiceImpl courseService;
	
	@Autowired
	public CourseController(CourseServiceImpl courseService) {
		super();
		this.courseService = courseService;
	}
	
	/**
	 * expose GET "/courses"
	 * get course list
	 *
	 * @param 
	 * @return ResponseEntity
	 */
	@GetMapping("")
	public ResponseEntity<Iterable<Course>> getAllCourses() {
		return ResponseEntity.of(Optional.ofNullable(courseService.getAllCourses()));
	}
	
	/**
	 * expose GET "/courses/{courseId}"
	 * get specific course
	 * 
	 * @param 
	 * @return ResponseEntity
	 */
	@GetMapping("/{courseId}")
	public ResponseEntity<Course> getCourse(@PathVariable int courseId) {
		return ResponseEntity.of(courseService.getCourseById(courseId));
	}
}
