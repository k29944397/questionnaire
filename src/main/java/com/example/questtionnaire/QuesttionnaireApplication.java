package com.example.questtionnaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class QuesttionnaireApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuesttionnaireApplication.class, args);
	}

}
