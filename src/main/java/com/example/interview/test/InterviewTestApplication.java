package com.example.interview.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class InterviewTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterviewTestApplication.class, args);
	}

}
