package api.services;

import api.models.EnvKey;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Scope("singleton")
public class EnvService {
    private Dotenv dotenv;

    @PostConstruct
    public void init() {
        this.dotenv = Dotenv.load();
    }

    public String getValue(EnvKey key) {
        return this.dotenv.get(key.name());
    }
}
