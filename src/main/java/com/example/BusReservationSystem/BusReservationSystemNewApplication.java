package com.example.BusReservationSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BusReservationSystemNewApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusReservationSystemNewApplication.class, args);
	}

}
