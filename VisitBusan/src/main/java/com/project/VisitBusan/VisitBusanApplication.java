package com.project.VisitBusan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class VisitBusanApplication {

	public static void main(String[] args) {

		SpringApplication.run(VisitBusanApplication.class, args);

	}

}
