package com.bundosRace.order;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;

@SpringBootApplication
@EnableFeignClients
public class OrderApplication {

	public static void main(String[] args) {

		SpringApplication.run(OrderApplication.class, args);

	}

	@Bean
	public RecordMessageConverter converter() {
		return new JsonMessageConverter();
	}

}
