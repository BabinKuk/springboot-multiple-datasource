package org.babinkuk.multidatasource.configuration;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.JndiDataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

/**
 * Basic data source configuration class
 */
public class BasicDataSourceConfiguration {

	protected DataSource configureDataSource(DataSourceProperties properties, ApplicationContext context) {
		
		if (StringUtils.isNotBlank(properties.getJndiName())) {
			JndiDataSourceAutoConfiguration configuration = new JndiDataSourceAutoConfiguration();
			return configuration.dataSource(properties, context);
		} else {
			return properties.initializeDataSourceBuilder().build();
		}
	}
}
