package it.eng.cepmiddleware;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.mashape.unirest.http.Unirest;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CEPMiddlewareApplication {

	public static void main(String[] args) {
		Unirest.setObjectMapper(ObjectMapperProvider.getObjectMapper());
		SpringApplication.run(CEPMiddlewareApplication.class, args);
	}

}