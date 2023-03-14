package com.example.app_for_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AppForTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppForTaskApplication.class, args);
	}

}
