package com.example.accountbalanceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class AccountBalanceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountBalanceServiceApplication.class, args);
	}

}
