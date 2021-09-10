package com.tarsidi.danesh.metric.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableKafka
@EnableScheduling
@ComponentScan
@ComponentScan(basePackages = "com.tiket.tix.flight.analytic.metric.lib.service.flux")
public class MetricExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetricExampleApplication.class, args);
	}

}
