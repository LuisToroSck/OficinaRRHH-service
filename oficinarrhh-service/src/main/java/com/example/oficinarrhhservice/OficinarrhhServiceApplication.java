package com.example.oficinarrhhservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OficinarrhhServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OficinarrhhServiceApplication.class, args);
	}

}
