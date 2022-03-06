package univevry.gestionperiscolaire;

import univevry.gestionperiscolaire.services.FakeDatabaseService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionPeriscolaireApplication {

	public static void main(String[] args) {
		FakeDatabaseService.initializeFakeUsers();

		SpringApplication.run(GestionPeriscolaireApplication.class, args);
	}

}
