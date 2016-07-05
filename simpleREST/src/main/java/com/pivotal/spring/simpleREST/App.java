package com.pivotal.spring.simpleREST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Hello world!
 *
 */
@Configuration
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
