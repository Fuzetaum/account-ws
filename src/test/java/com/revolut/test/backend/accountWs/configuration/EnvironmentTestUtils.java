package com.revolut.test.backend.accountWs.configuration;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.revolut.test.backend.accountWs.service.AccountService;
import com.revolut.test.backend.accountWs.service.BalanceService;
import com.revolut.test.backend.accountWs.service.JavalinApp;

public class EnvironmentTestUtils {
    private static boolean IS_ENVIRONMENT_LOADED = false;
    static WireMockServer MOCK_SERVER;

    public static void configure() throws InterruptedException {
        if (!IS_ENVIRONMENT_LOADED) {
            setDatabaseUrl();
            setDatabaseUsername();
            setDatabasePassword();
            setPort();
            Environment.loadApplicationConfiguration();
            JavalinApp.initialize();
            AccountService.getAccount();
            BalanceService.processDeposit();
            BalanceService.processWithdraw();
            MOCK_SERVER = new WireMockServer(9090);
            MOCK_SERVER.start();
            IS_ENVIRONMENT_LOADED = true;
        }
    }

    private static void setDatabaseUrl() { Environment.setEnvironmentVariable(Environment.DATABASE_URL, "jdbc:h2:mem:test;MODE=MYSQL;DB_CLOSE_DELAY=-1"); }
    private static void setDatabaseUsername() { Environment.setEnvironmentVariable(Environment.DATABASE_USERNAME, ""); }
    private static void setDatabasePassword() { Environment.setEnvironmentVariable(Environment.DATABASE_PASSWORD, ""); }
    private static void setPort() { Environment.setEnvironmentVariable(Environment.PORT, "8090"); }
}
