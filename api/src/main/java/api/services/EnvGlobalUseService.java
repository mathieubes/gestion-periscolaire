package api.services;

import api.models.EnvKey;

import java.util.Map;

public class EnvGlobalUseService {
    private static Map<EnvKey, String> env;

    public static void init(Map<EnvKey, String> environment) {
        env = environment;
    }

    public static String getValue(EnvKey key) {
        return env.get(key);
    }
}
