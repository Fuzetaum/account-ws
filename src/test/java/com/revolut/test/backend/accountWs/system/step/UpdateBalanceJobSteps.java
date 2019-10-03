package com.revolut.test.backend.accountWs.system.step;

import com.revolut.test.backend.accountWs.configuration.Environment;
import com.revolut.test.backend.accountWs.configuration.EnvironmentTestUtils;
import com.revolut.test.backend.accountWs.configuration.JooqConfiguration;
import com.revolut.test.backend.accountWs.database.tables.pojos.Account;
import com.revolut.test.backend.accountWs.database.tables.pojos.Operation;
import com.revolut.test.backend.accountWs.utils.HttpRequestPojo;
import com.revolut.test.backend.accountWs.utils.HttpRequestUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.revolut.test.backend.accountWs.database.Tables.ACCOUNT;
import static com.revolut.test.backend.accountWs.database.Tables.OPERATION;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UpdateBalanceJobSteps {
    private static Logger LOGGER = LoggerFactory.getLogger(UpdateBalanceJobSteps.class);
    private static HttpRequestPojo response;

    private static Map<String, Integer> balanceChanges = new HashMap<>();
    @Before("@UpdateBalanceJob")
    public void setUp() throws InterruptedException {
        EnvironmentTestUtils.configure();
        response = null;
    }

    @After("@UpdateBalanceJob")
    public void tearDown() {
        JooqConfiguration.getDslContext()
                .deleteFrom(OPERATION)
                .execute();
        JooqConfiguration.getDslContext()
                .deleteFrom(ACCOUNT)
                .execute();
    }

    @Given("^the following accounts exist$")
    public void theFollowingAccountsExist(List<Account> accounts) {
        JooqConfiguration.getAccountDao().insert(accounts);
    }

    @And("^the following operations exist$")
    public void theFollowingOperationsExist(List<Operation> operations) {
        JooqConfiguration.getOperationDao().insert(operations);
        operations.forEach(operation -> balanceChanges.compute(operation.getAccountId(), (accountId, balanceChange) ->
                balanceChange == null ? operation.getAmount() : operation.getAmount() + balanceChange));
    }

    @When("^UpdateBalanceJob runs$")
    public void updateBalanceJobRuns() throws IOException {
        String url = "http://localhost:" + Environment.get(Environment.PORT) + "/update-balances";
        String requestBody = "{\"replicas\": 1, \"instance\": 1}";
        response = HttpRequestUtils.sendPost(url, requestBody);
    }

    @Then("^system will report that no accounts were changed$")
    public void systemWillReportThatNoAccountsWereChanged() {
        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getResponse(), containsString("\"developerMessage\": \"" + 0 + "\""));
        assertThat(response.getResponse(), containsString("\"userMessage\": \"Account balances updated: " + 0 + "\""));
    }

    @Then("^system will report that (\\d+) accounts were changed$")
    public void systemWillReportThatAccountsWereChanged(Integer amount, List<Account> updatedAccounts) {
        assertThat(response.getStatus(), is(equalTo(200)));
        assertThat(response.getResponse(), containsString("\"developerMessage\": \"" + amount + "\""));
        assertThat(response.getResponse(), containsString("\"userMessage\": \"Account balances updated: " + amount + "\""));
    }
}
