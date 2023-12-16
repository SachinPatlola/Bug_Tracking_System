package com.bugtrackingsystem;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;


import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
public class BugTrackingSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(BugTrackingSystemApplication.class, args);
	}
}
