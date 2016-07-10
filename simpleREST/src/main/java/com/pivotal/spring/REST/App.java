package com.pivotal.spring.REST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 *
 */
@ComponentScan(basePackages={"com.pivotal.spring.REST"})
@SpringBootApplication
public class App extends SpringBootServletInitializer{
	public static void main(String[] args) {
		new SpringApplication(App.class).run(
				args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) { 
		this.setRegisterErrorPageFilter(false);
		return application.sources(App.class);
	}
}
