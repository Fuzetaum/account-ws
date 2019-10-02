package com.revolut.test.backend.accountWs.system.step;

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
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RetryWithdrawJobSteps {
    private static Logger LOGGER = LoggerFactory.getLogger(RetryWithdrawJobSteps.class);
    private static HttpRequestPojo response;

//    @Before("@RetryWithdrawJob")
//    public void setUp() throws InterruptedException {
//        EnvironmentTestUtils.configure();
//        response = null;
//    }
//
//    @After("@RetryWithdrawJob")
//    public void tearDownJob() {
//        JooqConfiguration.getDslContext()
//                .deleteFrom(PENDING_WITHDRAW)
//                .execute();
//        JooqConfiguration.getDslContext()
//                .deleteFrom(TRANSFER_ATTEMPT)
//                .execute();
//        JooqConfiguration.getDslContext()
//                .deleteFrom(TRANSFER)
//                .execute();
//    }
//
//    @After("@Success")
//    public void tearDownSuccess() throws IOException {
//        HttpRequestUtils.sendPost("http://localhost:9090/__admin/reset", "");
//    }
//
//    @Given("^there are no pending withdraws$")
//    public void thereAreNoPendingWithdraws() {}
//
//    @Given("^the following withdraws are pending$")
//    public void theFollowingWithdrawsArePending(List<Transfer> transfers) {
//        transfers.forEach(transfer -> {
//            JooqConfiguration.getTransferDao()
//                    .insert(transfer);
//            JooqConfiguration.getPendingWithdrawDao()
//                    .insert(new PendingWithdraw(transfer.getId()));
//        });
//    }
//
//    @And("^account WS validates the following accounts$")
//    public void accountWSValidatesTheFollowingAccounts(List<String> ids) {
//        configureFor("localhost", 9090);
//        ids.forEach(transferId -> {
//            String responseBody = "{ \"developerMessage\": \"message\", \"userMessage\": \"message\" }";
//            stubFor(post(urlPathEqualTo("/account/" + transferId + "/withdraw")
//                ).withQueryParam("amount", matching("(\\d+)"))
//                    .willReturn(aResponse().withStatus(200).withBody(responseBody)));
//            stubFor(post(urlPathEqualTo("/account/" + transferId + "/deposit")
//            ).withQueryParam("amount", matching("(\\d+)"))
//                    .willReturn(aResponse().withStatus(200).withBody(responseBody)));
//        });
//    }
//
//    @When("^RetryWithdrawJob runs$")
//    public void retryWithdrawJobRuns() throws IOException {
//        String url = "http://localhost:" + Environment.get(Environment.PORT) + "/retry-withdraws";
//        String requestBody = "{\"replicas\": 1, \"instance\": 1}";
//        response = HttpRequestUtils.sendPost(url, requestBody);
//    }
//
//    @Then("^system will report that no withdraws were processed$")
//    public void systemWillReportThatNoWithdrawsWereProcessed() {
//        assertThat(response.getStatus(), is(equalTo(200)));
//        assertThat(response.getResponse(), containsString("\"developerMessage\": \"0\""));
//        assertThat(response.getResponse(), containsString("\"userMessage\": \"Withdraws reattempted: 0\""));
//    }
//
//    @Then("^system will report that (\\d+) withdraws were processed$")
//    public void systemWillReportThatWithdrawsWereProcessed(Integer amount) {
//        assertThat(response.getStatus(), is(equalTo(200)));
//        assertThat(response.getResponse(), containsString("\"developerMessage\": \"" + amount + "\""));
//        assertThat(response.getResponse(), containsString("\"userMessage\": \"Withdraws reattempted: " + amount + "\""));
//    }
}
