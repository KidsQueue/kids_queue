package com.kidsqueue.kidsqueue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class KidsQueueApplication {

	public static void main(String[] args) {
		SpringApplication.run(KidsQueueApplication.class, args);
	}

}
