package com.phoenix.howabouttoday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HowAboutTodayApplication {
	public static void main(String[] args) {
		SpringApplication.run(HowAboutTodayApplication.class, args);
	}

}