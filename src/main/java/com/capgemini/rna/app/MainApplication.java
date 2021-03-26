package com.capgemini.rna.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.AsyncConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = {"com.capgemini.rna"})
public class MainApplication implements AsyncConfigurer {
	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

}
