package com.pivotal.spring.simpleREST;

import javax.activation.DataSource;

import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource() {
        CloudFactory cloudFactory = new CloudFactory();
        Cloud cloud = cloudFactory.getCloud();
         ServiceInfo serviceInfo = cloud.getServiceInfo("cf-spring-db");
        return cloud.getServiceConnector(serviceInfo.getId(), DataSource.class, null);
    }
}
}
