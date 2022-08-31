package com.example.iamscratches.guestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class GuestserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestserviceApplication.class, args);
	}

}
