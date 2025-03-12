package org.babinkuk.multidatasource.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

import javax.sql.DataSource;

import static org.babinkuk.multidatasource.configuration.Qualifiers.Datasource.JOB_PORTAL_DS_JDBC_TEMPLATE;
import static org.babinkuk.multidatasource.configuration.Qualifiers.Datasource.JOB_PORTAL_DS_NP_JDBC_TEMPLATE;
import static org.babinkuk.multidatasource.configuration.Qualifiers.Datasource.JOB_PORTAL_PROPERTIES_NAME;

/**
 * JobPortal data source configuration class
 */
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = Qualifiers.EntityManagerFactory.JOB_PORTAL, // entityManagerFactoryRef bean name
        transactionManagerRef = Qualifiers.TransactionManagerFactory.JOB_PORTAL, // transactionManagerRef bean name
        basePackages = {Qualifiers.Datasource.JOB_PORTAL_REPOSITORY_PACKAGE}) // jpa repository package
public class JobPortalDataSourceConfiguration extends BasicDataSourceConfiguration {
	
	/**
	 * Bean mapping DataSourceProperties from spring.datasource.jobportal in app context
	 */
	@Bean(JOB_PORTAL_PROPERTIES_NAME)
	@ConfigurationProperties(prefix = Qualifiers.Datasource.JOB_PORTAL_CONFIGURATION_PREFIX)
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}
	
	/**
	 * Bean creating data sources by using the DataSourceProperties objects
	 */
	@Bean(Qualifiers.Datasource.JOB_PORTAL)
	public DataSource jobPortalDs(ApplicationContext context) {
	DataSourceProperties dataSourceProperties = dataSourceProperties();
	 	return configureDataSource(dataSourceProperties, context);
	}
	
	/**
	 * Bean configuring one instance of JdbcTemplate for jobPortal DataSource
	 */
	@Bean(name = JOB_PORTAL_DS_JDBC_TEMPLATE)
	public JdbcTemplate jdbcTemplate(@Qualifier(Qualifiers.Datasource.JOB_PORTAL) DataSource ds) {
		return new JdbcTemplate(ds);
	}
	
	/**
	 * Bean configuring one instance of NamedParameterJdbcTemplate for jobPortal DataSource
	 */
	@Bean(name = JOB_PORTAL_DS_NP_JDBC_TEMPLATE)
	public NamedParameterJdbcTemplate npJdbcTemplate(@Qualifier(Qualifiers.Datasource.JOB_PORTAL) DataSource ds) {
		return new NamedParameterJdbcTemplate(ds);
	}
	
	/**
	 * Bean configuring one instance of LocalContainerEntityManagerFactoryBean for jobPortal DataSource
	 */
	@Bean(name = Qualifiers.EntityManagerFactory.JOB_PORTAL)
	public LocalContainerEntityManagerFactoryBean jobportalentityManagerFactory(
			EntityManagerFactoryBuilder builder,
			@Qualifier(Qualifiers.Datasource.JOB_PORTAL) DataSource dataSource) {
		return builder
				.dataSource(dataSource)
				.packages(Qualifiers.Datasource.JOB_PORTAL_ENTITY_PACKAGE) // entity package
				.persistenceUnit(Qualifiers.Datasource.JOB_PORTAL_PERSISTENCE_NAME)
				.build();
	}

	/**
	 * Bean configuring one instance of TransactionManager for jobPortal DataSource
	 */
	@Bean(name = Qualifiers.TransactionManagerFactory.JOB_PORTAL)
	public PlatformTransactionManager jobportalTransactionManagerFactory(
			@Qualifier(Qualifiers.EntityManagerFactory.JOB_PORTAL) EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
