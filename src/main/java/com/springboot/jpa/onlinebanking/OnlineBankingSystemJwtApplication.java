package com.springboot.jpa.onlinebanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OnlineBankingSystemJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBankingSystemJwtApplication.class, args);
	}

	@Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }
}
