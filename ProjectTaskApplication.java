package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@ComponentScan({"com.demo.projectTask","com.model", "com.dao", "com.service","com.security","com.controller"})
@EntityScan("com.model")
@EnableJpaRepositories("com.dao")
public class ProjectTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectTaskApplication.class, args);
	}

}
