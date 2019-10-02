package com.revolut.test.backend.accountWs.configuration;

import com.revolut.test.backend.accountWs.configuration.gson.GsonConfiguration;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    private static Map<String, String> ENVIRONMENT_VARIABLES = new HashMap<>();

    static String DATABASE_PASSWORD = "DATABASE_PASSWORD";
    static String DATABASE_URL = "DATABASE_URL";
    static String DATABASE_USERNAME = "DATABASE_USERNAME";
    public static String BALANCE_MAX_NEGATIVE = "BALANCE_MAX_NEGATIVE";
    public static String PORT = "PORT";

    public static String get(String varName) { return ENVIRONMENT_VARIABLES.get(varName); }

    public static void loadEnvironmentVariables() {
        String DATABASE_PORT = "DATABASE_PORT";
        String DATABASE_SCHEMA = "DATABASE_SCHEMA";
        String databaseUrl = "jdbc:mysql://" + System.getenv(DATABASE_URL) + ":" + System.getenv(DATABASE_PORT)
                + "/" + System.getenv(DATABASE_SCHEMA) + "?serverTimezone=UTC";

        ENVIRONMENT_VARIABLES.put(DATABASE_PASSWORD, System.getenv(DATABASE_PASSWORD));
        ENVIRONMENT_VARIABLES.put(DATABASE_URL, databaseUrl);
        ENVIRONMENT_VARIABLES.put(DATABASE_USERNAME, System.getenv(DATABASE_USERNAME));
        ENVIRONMENT_VARIABLES.put(BALANCE_MAX_NEGATIVE, System.getenv(BALANCE_MAX_NEGATIVE));
        ENVIRONMENT_VARIABLES.put(PORT, System.getenv(PORT));
    }

    public static void loadApplicationConfiguration() throws InterruptedException {
        FlywayConfiguration.configure();
        JooqConfiguration.configure();
        GsonConfiguration.configure();
    }

    static void setEnvironmentVariable(String name, String value) {
        ENVIRONMENT_VARIABLES.put(name, value);
    }
}
