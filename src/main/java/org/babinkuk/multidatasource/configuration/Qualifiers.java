package org.babinkuk.multidatasource.configuration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.annotation.Qualifier;

public class Qualifiers {
	
	public static class Datasource {
	
		public static final String JOB_PORTAL_CONFIGURATION_PREFIX = "spring.datasource.jobportal";
		public static final String COURSE_TRACKER_CONFIGURATION_PREFIX = "spring.datasource.coursetracker";
		
		public static final String JOB_PORTAL = "org.babinkuk.jobportal.datasource";
		public static final String COURSE_TRACKER = "org.babinkuk.coursetracker.datasource";
		
		public static final String JOB_PORTAL_PROPERTIES_NAME = "jobportalDsProperties";
		public static final String COURSE_TRACKER_PROPERTIES_NAME = "coursetrackerDsProperties";
		
		public static final String JOB_PORTAL_DS_JDBC_TEMPLATE = "jobportalDsJdbcTemplate";
		public static final String COURSE_TRACKER_DS_JDBC_TEMPLATE = "coursetrackerDsJdbcTemplate";
		
		public static final String JOB_PORTAL_DS_NP_JDBC_TEMPLATE = "jobportalDsNpJdbcTemplate";
		public static final String COURSE_TRACKER_DS_NP_JDBC_TEMPLATE = "coursetrackerDsNpJdbcTemplate";
		
		public static final String JOB_PORTAL_REPOSITORY_PACKAGE = "org.babinkuk.multidatasource.repository.jobportal";
		public static final String COURSE_TRACKER_REPOSITORY_PACKAGE = "org.babinkuk.multidatasource.repository.coursetracker";
		
		public static final String JOB_PORTAL_ENTITY_PACKAGE = "org.babinkuk.multidatasource.entity.jobportal";
		public static final String COURSE_TRACKER_ENTITY_PACKAGE = "org.babinkuk.multidatasource.entity.coursetracker";
	
		public static final String JOB_PORTAL_PERSISTENCE_NAME = "JOBPORTAL";
		public static final String COURSE_TRACKER_PERSISTENCE_NAME = "COURSETRACKER";
	
		@Qualifier(JOB_PORTAL)
		@Retention(RetentionPolicy.RUNTIME)
		public @interface JobPortal {}
		
		@Qualifier(COURSE_TRACKER)
		@Retention(RetentionPolicy.RUNTIME)
		public @interface CourseTracker {}
	}

	/*
	 * declare EntityManager factories for each data source 
	 */
	public static class EntityManagerFactory {
		public static final String JOB_PORTAL = "jobportalEntityManagerFactory";
		public static final String COURSE_TRACKER = "coursetrackerEntityManagerFactory";
	}
	
	/*
	 * declare TransactionManager factories for each data source 
	 */
	public static class TransactionManagerFactory {
		public static final String JOB_PORTAL = "jobportalTransactionManager";
		public static final String COURSE_TRACKER = "coursetrackerTransactionManager";
	}
}
