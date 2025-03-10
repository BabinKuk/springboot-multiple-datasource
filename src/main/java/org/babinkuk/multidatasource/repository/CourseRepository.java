package org.babinkuk.multidatasource.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import static org.babinkuk.multidatasource.configuration.Qualifiers.Datasource.COURSE_TRACKER_DS_JDBC_TEMPLATE;

import java.util.List;

@Repository
public class CourseRepository {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private final JdbcTemplate jdbcTemplate;
	
	public CourseRepository(@Qualifier(COURSE_TRACKER_DS_JDBC_TEMPLATE) JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<String> getAllCourses() {
	
		String str = "SELECT title FROM course";
		
		List<String> courses = jdbcTemplate.queryForList(
					str,
					String.class);
		
		log.info("courses {}", courses.size());
		courses.forEach(log::info);
		
		return courses;
	}
}
