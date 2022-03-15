package api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import api.services.EnvGlobalUseService;
import api.services.EnvService;

@SpringBootApplication
public class ApiApplication {
	private final static String ENV_PATH = ".env";

	public static void main(String[] args) {
		initEnvironmentVariables();

		SpringApplication.run(ApiApplication.class, args);
	}

	private static void initEnvironmentVariables() {
		final var env = new EnvService(ENV_PATH);
		EnvGlobalUseService.init(env.getEnv());
	}
}
