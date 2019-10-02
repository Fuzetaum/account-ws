package com.revolut.test.backend.accountWs;

import com.revolut.test.backend.accountWs.configuration.Environment;
import com.revolut.test.backend.accountWs.job.UpdateBalanceJob;
import com.revolut.test.backend.accountWs.service.AccountService;
import com.revolut.test.backend.accountWs.service.BalanceService;
import com.revolut.test.backend.accountWs.service.JavalinApp;

public class Main {
    public static void main(String[] args) {
        try {
            Environment.loadEnvironmentVariables();
            Environment.loadApplicationConfiguration();
            JavalinApp.initialize();
            AccountService.getAccount();
            BalanceService.processDeposit();
            BalanceService.processWithdraw();
            UpdateBalanceJob.updateBalances();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
