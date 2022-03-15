package api.services.env;

import java.util.Map;

import api.models.env.EnvKey;

public class EnvGlobalUseService {
    private static Map<EnvKey, String> env;

    public static void init(Map<EnvKey, String> environment) {
        env = environment;
    }

    public static String getValue(EnvKey key) {
        return env.get(key);
    }
}
