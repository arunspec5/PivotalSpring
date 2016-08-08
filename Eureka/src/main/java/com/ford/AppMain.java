package com.ford;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
public class AppMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(AppMain.class, args);
	}

}
