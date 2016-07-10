package com.pivotal.spring.simpleREST;

import javax.sql.DataSource;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.pivotal.spring.REST.User;
import com.pivotal.spring.REST.UserRepository;



@Configuration
@Profile("cloud")
@EntityScan(basePackageClasses=User.class)
@EnableJpaRepositories(basePackageClasses=UserRepository.class)
public class DataSourceConfig extends AbstractCloudConfig {
    @Bean
    public DataSource dataSource() {
        return connectionFactory().dataSource();
    }
}
