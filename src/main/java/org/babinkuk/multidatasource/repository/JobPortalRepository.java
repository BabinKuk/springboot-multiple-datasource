package org.babinkuk.multidatasource.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import static org.babinkuk.multidatasource.configuration.Qualifiers.Datasource.JOB_PORTAL_DS_JDBC_TEMPLATE;

import java.util.List;

@Repository
public class JobPortalRepository {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private final JdbcTemplate jdbcTemplate;
	
	public JobPortalRepository(@Qualifier(JOB_PORTAL_DS_JDBC_TEMPLATE) JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<String> getAllUsers() {
	
		String str = "SELECT email FROM users";
		
		List<String> users = jdbcTemplate.queryForList(
					str,
					String.class);
		
		log.info("users {}", users.size());
		users.forEach(log::info);
		
		return users;
	}
}
