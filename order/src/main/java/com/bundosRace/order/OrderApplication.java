package com.bundosRace.order;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

		// 환경 변수 읽기
		String jwtSecret = dotenv.get("JWT_SECRET");

		SpringApplication.run(OrderApplication.class, args);
	}

}
