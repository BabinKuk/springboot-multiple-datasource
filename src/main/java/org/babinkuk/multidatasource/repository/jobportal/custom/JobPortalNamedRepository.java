package org.babinkuk.multidatasource.repository.jobportal.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import static org.babinkuk.multidatasource.configuration.Qualifiers.Datasource.JOB_PORTAL_DS_NP_JDBC_TEMPLATE;

import java.util.List;

@Repository
public class JobPortalNamedRepository {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	public JobPortalNamedRepository(@Qualifier(JOB_PORTAL_DS_NP_JDBC_TEMPLATE) NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<String> getAllSkillNames(String level) {
	
		String str = "SELECT name FROM skills WHERE experience_level=:level";
		
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("level", level);
		
		List<String> skills = jdbcTemplate.query(
					str,
					namedParameters,
					(rs, i) -> {
						if (rs.next()) {
							return rs.getString(1);
						}
						return "";
					});
		
		log.info("skills {}", skills.size());
		skills.forEach(log::info);
		
		return skills;
	}
}
