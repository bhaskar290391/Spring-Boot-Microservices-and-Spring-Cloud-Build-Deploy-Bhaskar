package com.appdeveloperblogs.photoapp.accounts.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PhotoappAccountManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoappAccountManagementServiceApplication.class, args);
	}

}
