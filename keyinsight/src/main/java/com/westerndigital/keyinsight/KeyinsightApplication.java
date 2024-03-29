package com.westerndigital.keyinsight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KeyinsightApplication {

	public static void main(String[] args){
		SpringApplication.run(KeyinsightApplication.class, args);
	}

}