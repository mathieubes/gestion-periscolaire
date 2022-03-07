package api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import api.services.FakeDatabaseService;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		FakeDatabaseService.initializeFakeUsers();
		SpringApplication.run(ApiApplication.class, args);
	}

}
