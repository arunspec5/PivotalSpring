package com.pivotal.spring.simpleREST;

import javax.activation.DataSource;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.ServiceInfo;
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
public class DataSourceConfig {
    @Bean
    public DataSource dataSource() {
        CloudFactory cloudFactory = new CloudFactory();
        Cloud cloud = cloudFactory.getCloud();
         ServiceInfo serviceInfo = cloud.getServiceInfo("cf-spring-db");
         System.out.println("serviceInfo "+serviceInfo.getId());
        return cloud.getServiceConnector(serviceInfo.getId(), DataSource.class, null);
    }
}
