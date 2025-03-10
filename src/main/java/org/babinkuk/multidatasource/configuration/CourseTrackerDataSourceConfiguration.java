package org.babinkuk.multidatasource.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

import static org.babinkuk.multidatasource.configuration.Qualifiers.Datasource.COURSE_TRACKER_DS_JDBC_TEMPLATE;
import static org.babinkuk.multidatasource.configuration.Qualifiers.Datasource.COURSE_TRACKER_DS_NP_JDBC_TEMPLATE;
import static org.babinkuk.multidatasource.configuration.Qualifiers.Datasource.COURSE_TRACKER_PROPERTIES_NAME;

@Configuration
public class CourseTrackerDataSourceConfiguration extends BasicDataSourceConfiguration {

	 @Bean(COURSE_TRACKER_PROPERTIES_NAME)
	 @ConfigurationProperties(prefix = Qualifiers.Datasource.COURSE_TRACKER_CONFIGURATION_PREFIX)
	 public DataSourceProperties dataSourceProperties() {
		 return new DataSourceProperties();
	 }
	 
	 @Bean(Qualifiers.Datasource.COURSE_TRACKER)
	 @Primary
	 public DataSource jobPortalDs(ApplicationContext context) {
		 DataSourceProperties dataSourceProperties = dataSourceProperties();
		 return configureDataSource(dataSourceProperties, context);
	 }
	 
	 @Bean(name = COURSE_TRACKER_DS_JDBC_TEMPLATE)
	 @Primary
	 public JdbcTemplate jdbcTemplate(@Qualifier(Qualifiers.Datasource.COURSE_TRACKER) DataSource ds) {
		 return new JdbcTemplate(ds);
	 }
	 
	 @Bean(name = COURSE_TRACKER_DS_NP_JDBC_TEMPLATE)
	 @Primary
	 public NamedParameterJdbcTemplate npJdbcTemplate(@Qualifier(Qualifiers.Datasource.COURSE_TRACKER) DataSource ds) {
		 return new NamedParameterJdbcTemplate(ds);
	 }
}
