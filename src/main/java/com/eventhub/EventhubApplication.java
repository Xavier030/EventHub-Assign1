package com.eventhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EventhubApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventhubApplication.class, args);
	}

}
