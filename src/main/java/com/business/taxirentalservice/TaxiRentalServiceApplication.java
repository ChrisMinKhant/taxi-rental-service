package com.business.taxirentalservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class TaxiRentalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxiRentalServiceApplication.class, args);
	}
}
