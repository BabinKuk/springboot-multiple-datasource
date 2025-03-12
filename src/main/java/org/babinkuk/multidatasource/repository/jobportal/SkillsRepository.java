package org.babinkuk.multidatasource.repository.jobportal;

import java.util.List;

import org.babinkuk.multidatasource.entity.jobportal.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Integer> {
	List<Skills> findAll();
	
	@Query(value = "SELECT * FROM skills s " +
			" WHERE 1=1" +
			" AND UPPER(s.experience_level)=UPPER(:level)", nativeQuery = true)
	List<Skills> findByLevel(String level);
}
