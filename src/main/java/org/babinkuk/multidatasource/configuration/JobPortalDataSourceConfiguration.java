package org.babinkuk.multidatasource.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

import static org.babinkuk.multidatasource.configuration.Qualifiers.Datasource.JOB_PORTAL_DS_JDBC_TEMPLATE;
import static org.babinkuk.multidatasource.configuration.Qualifiers.Datasource.JOB_PORTAL_DS_NP_JDBC_TEMPLATE;
import static org.babinkuk.multidatasource.configuration.Qualifiers.Datasource.JOB_PORTAL_PROPERTIES_NAME;

@Configuration
public class JobPortalDataSourceConfiguration extends BasicDataSourceConfiguration {

	 @Bean(JOB_PORTAL_PROPERTIES_NAME)
	 @ConfigurationProperties(prefix = Qualifiers.Datasource.JOB_PORTAL_CONFIGURATION_PREFIX)
	 public DataSourceProperties dataSourceProperties() {
		 return new DataSourceProperties();
	 }
	 
	 @Bean(Qualifiers.Datasource.JOB_PORTAL)
	 public DataSource jobPortalDs(ApplicationContext context) {
		 DataSourceProperties dataSourceProperties = dataSourceProperties();
		 return configureDataSource(dataSourceProperties, context);
	 }
	 
	 @Bean(name = JOB_PORTAL_DS_JDBC_TEMPLATE)
	 public JdbcTemplate jdbcTemplate(@Qualifier(Qualifiers.Datasource.JOB_PORTAL) DataSource ds) {
		 return new JdbcTemplate(ds);
	 }
	 
	 @Bean(name = JOB_PORTAL_DS_NP_JDBC_TEMPLATE)
	 public NamedParameterJdbcTemplate npJdbcTemplate(@Qualifier(Qualifiers.Datasource.JOB_PORTAL) DataSource ds) {
		 return new NamedParameterJdbcTemplate(ds);
	 }
}
