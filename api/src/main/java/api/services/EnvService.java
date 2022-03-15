package api.services;

import api.models.EnvKey;
import io.github.cdimascio.dotenv.Dotenv;

public class EnvService {
    private static Dotenv dotenv;

    public static void init() {
        dotenv = Dotenv.load();
    }

    public static String getValue(EnvKey key) {
        return dotenv.get(key.name());
    }
}
