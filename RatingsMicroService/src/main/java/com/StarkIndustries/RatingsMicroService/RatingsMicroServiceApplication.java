package com.StarkIndustries.RatingsMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RatingsMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingsMicroServiceApplication.class, args);
	}

}
