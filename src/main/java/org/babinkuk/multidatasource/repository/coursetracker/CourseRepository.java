package org.babinkuk.multidatasource.repository.coursetracker;

import java.util.List;

import org.babinkuk.multidatasource.entity.coursetracker.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
	List<Course> findAll();
}
