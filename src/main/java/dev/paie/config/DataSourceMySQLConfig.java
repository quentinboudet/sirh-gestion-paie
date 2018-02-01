package dev.paie.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DataSourceMySQLConfig {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://b4dyykv93-mysql.services.clever-cloud.com:3306/b4dyykv93?useSSL=false");
		dataSource.setUsername("umhxrn9mgitnbwhw");
		dataSource.setPassword("WLLtY1PlOGRk4j6e1oH");
		return dataSource;
	}

//	@Bean
//	public DataSource datasource() {
//		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("schema.sql")
//				.addScript("test-data.sql").build();
//	}
}