package com.pivotal.spring.REST;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.config.java.ServiceScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;




@Configuration
@Profile("cloud")
@ServiceScan
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@EnableJpaRepositories(basePackageClasses=UserRepository.class)
public class DataSourceConfig extends AbstractCloudConfig {
	@Bean(name="cf-spring-db")
    public DataSource dataSource() {
        return connectionFactory().dataSource();
    }
}
