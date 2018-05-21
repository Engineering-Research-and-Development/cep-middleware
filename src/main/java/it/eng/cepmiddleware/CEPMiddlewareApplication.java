package it.eng.cepmiddleware;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mashape.unirest.http.Unirest;

@SpringBootApplication
public class CEPMiddlewareApplication {

	public static void main(String[] args) {
		Unirest.setObjectMapper(new UnirestObjectMapper());
		SpringApplication.run(CEPMiddlewareApplication.class, args);
	}

}