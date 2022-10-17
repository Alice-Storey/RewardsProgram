package com.alice.RewardsProgram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableAutoConfiguration
@ComponentScan
public class RewardsProgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardsProgramApplication.class, args);
	}

}
