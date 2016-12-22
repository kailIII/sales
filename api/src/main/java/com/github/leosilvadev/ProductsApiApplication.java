package com.github.leosilvadev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ProductsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsApiApplication.class, args);
		configureDatabase();
	}

	public static void configureDatabase() {
		String url = System.getenv("DB_URL");
		if ( url != null && !url.isEmpty() ) {
			System.setProperty("spring.datasource.url", url);
		}

		String username = System.getenv("DB_USERNAME");
		if ( username != null && !username.isEmpty() ) {
			System.setProperty("spring.datasource.username", username);
		}
		
		String password = System.getenv("DB_PASSWORD");
		if ( password != null && !password.isEmpty() ) {
			System.setProperty("spring.datasource.password", password);
		}
	}

}
