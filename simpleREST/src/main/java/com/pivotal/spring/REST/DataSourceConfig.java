package com.pivotal.spring.REST;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.config.java.ServiceScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;




@Configuration
@Profile("cloud")
@ServiceScan
@EnableAutoConfiguration
public class DataSourceConfig extends AbstractCloudConfig {
	@Bean(name = "cf-spring-db")
    @Qualifier("cf-spring-db")
	@Primary
    public DataSource dataSource() {
        return connectionFactory().dataSource();
    }
}
