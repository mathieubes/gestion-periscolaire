package api;

import api.services.EnvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {
	public static void main(String[] args) {
		EnvService.init();

		SpringApplication.run(ApiApplication.class, args);
	}
}
