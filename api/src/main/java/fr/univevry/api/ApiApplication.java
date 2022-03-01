package fr.univevry.api;

import fr.univevry.api.services.FakeDatabaseService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        FakeDatabaseService.initializeFakeUsers();

        SpringApplication.run(ApiApplication.class, args);
    }

}
