package com.ayan.tiles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TestOneApplication {

	 @Bean
	 public RestTemplate getRestTemplate() {
		 return new RestTemplate();
	 }
	 
	 @Bean
	    BCryptPasswordEncoder bCryptPasswordEncoder(){
	        return new BCryptPasswordEncoder();
	    }
	 
	public static void main(String[] args) {
		SpringApplication.run(TestOneApplication.class, args);
	}

}
