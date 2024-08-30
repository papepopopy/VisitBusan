package com.project.VisitBusan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing  // baseEntity가 Auditing하는데 필요
@SpringBootApplication
public class VisitBusanApplication {

	public static void main(String[] args) {

		SpringApplication.run(VisitBusanApplication.class, args);

	}

}
