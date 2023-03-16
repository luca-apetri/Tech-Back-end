package com.IntelligentForms.Intelligent_Forms_FCR;

import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class IntelligentFormsFcrApplication {

	public static void main(String[] args) {

		SpringApplication.run(IntelligentFormsFcrApplication.class, args);
	}

}
