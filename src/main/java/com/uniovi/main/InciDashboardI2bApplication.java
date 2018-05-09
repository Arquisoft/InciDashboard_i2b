package com.uniovi.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication

@ComponentScan({
	"com.uniovi.repositories",
	"com.uniovi.dashboard",
	"com.uniovi.entities",
	"com.uniovi.config",
	"com.uniovi.kafka",
	"com.uniovi.services",
	"com.uniovi.util"
	})
@EnableMongoRepositories("com.uniovi.repositories")
public class InciDashboardI2bApplication {

	public static void main(String[] args) {
		SpringApplication.run(InciDashboardI2bApplication.class, args);
	}
}
