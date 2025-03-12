package org.babinkuk.multidatasource.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

import javax.sql.DataSource;

import static org.babinkuk.multidatasource.configuration.Qualifiers.Datasource.COURSE_TRACKER_DS_JDBC_TEMPLATE;
import static org.babinkuk.multidatasource.configuration.Qualifiers.Datasource.COURSE_TRACKER_DS_NP_JDBC_TEMPLATE;
import static org.babinkuk.multidatasource.configuration.Qualifiers.Datasource.COURSE_TRACKER_PROPERTIES_NAME;

/**
 * CourseTracker data source configuration class
 */
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = Qualifiers.EntityManagerFactory.COURSE_TRACKER,
        transactionManagerRef = Qualifiers.TransactionManagerFactory.COURSE_TRACKER,
        basePackages = {Qualifiers.Datasource.COURSE_TRACKER_REPOSITORY_PACKAGE})
public class CourseTrackerDataSourceConfiguration extends BasicDataSourceConfiguration {

	/**
	 * Bean mapping DataSourceProperties from spring.datasource.coursetracker in app context
	 */
	@Bean(COURSE_TRACKER_PROPERTIES_NAME)
	@ConfigurationProperties(prefix = Qualifiers.Datasource.COURSE_TRACKER_CONFIGURATION_PREFIX)
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}
	
	/**
	 * Bean creating data sources by using the DataSourceProperties objects
	 */
	@Bean(Qualifiers.Datasource.COURSE_TRACKER)
	@Primary
	public DataSource jobPortalDs(ApplicationContext context) {
		DataSourceProperties dataSourceProperties = dataSourceProperties();
		return configureDataSource(dataSourceProperties, context);
	}
	
	/**
	 * Bean configuring one instance of JdbcTemplate for coursetracker DataSource
	 */
	@Bean(name = COURSE_TRACKER_DS_JDBC_TEMPLATE)
	@Primary
	public JdbcTemplate jdbcTemplate(@Qualifier(Qualifiers.Datasource.COURSE_TRACKER) DataSource ds) {
		return new JdbcTemplate(ds);
	}
	
	/**
	 * Bean configuring one instance of NamedParameterJdbcTemplate for coursetracker DataSource
	 */
	@Bean(name = COURSE_TRACKER_DS_NP_JDBC_TEMPLATE)
	@Primary
	public NamedParameterJdbcTemplate npJdbcTemplate(@Qualifier(Qualifiers.Datasource.COURSE_TRACKER) DataSource ds) {
		return new NamedParameterJdbcTemplate(ds);
	}
	
	@Bean(name = Qualifiers.EntityManagerFactory.COURSE_TRACKER)
	@Primary
	public LocalContainerEntityManagerFactoryBean coursetrackerEntityManagerFactory(
			EntityManagerFactoryBuilder builder,
			@Qualifier(Qualifiers.Datasource.COURSE_TRACKER) DataSource dataSource) {
		return builder
				.dataSource(dataSource)
				.packages(Qualifiers.Datasource.COURSE_TRACKER_ENTITY_PACKAGE)
				.persistenceUnit(Qualifiers.Datasource.COURSE_TRACKER_PERSISTENCE_NAME)
				.build();
	}

	@Bean(name = Qualifiers.TransactionManagerFactory.COURSE_TRACKER)
	public PlatformTransactionManager coursetrackerTransactionManagerFactory(@Qualifier(Qualifiers.EntityManagerFactory.COURSE_TRACKER) EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
