package com.revolut.test.backend.accountWs.service;

import com.google.gson.JsonSyntaxException;
import com.revolut.test.backend.accountWs.configuration.Environment;
import com.revolut.test.backend.accountWs.configuration.JooqConfiguration;
import com.revolut.test.backend.accountWs.database.tables.pojos.Account;
import com.revolut.test.backend.accountWs.database.tables.pojos.Operation;
import com.revolut.test.backend.accountWs.model.BalanceRequestPojo;
import com.revolut.test.backend.accountWs.model.Currency;
import com.revolut.test.backend.accountWs.model.ResponsePojo;
import com.revolut.test.backend.accountWs.utils.RequestUtils;
import org.jooq.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.jooq.impl.DSL.sum;

public class BalanceService {
    private static Logger LOGGER = LoggerFactory.getLogger(BalanceService.class);

    public static void processDeposit() {
        JavalinApp.post("/account/:id/deposit", ctx -> {
            String id = ctx.pathParam("id");
            try {
                BalanceRequestPojo transferRequest = (BalanceRequestPojo) RequestUtils.fromJson(ctx.body(), BalanceRequestPojo.class);
                return processTransaction(id, transferRequest, false);
            } catch (JsonSyntaxException jse) {
                ResponsePojo response = ResponsePojo.REQUEST_ERROR_INVALID_AMOUNT_FORMAT();
                LOGGER.error(response.getDeveloperMessage());
                return response;
            }
        });
    }

    public static void processWithdraw() {
        JavalinApp.post("/account/:id/withdraw", ctx -> {
            String id = ctx.pathParam("id");
            try {
                BalanceRequestPojo transferRequest = (BalanceRequestPojo) RequestUtils.fromJson(ctx.body(), BalanceRequestPojo.class);
                return processTransaction(id, transferRequest, true);
            } catch (JsonSyntaxException jse) {
                ResponsePojo response = ResponsePojo.REQUEST_ERROR_INVALID_AMOUNT_FORMAT();
                LOGGER.error(response.getDeveloperMessage());
                return response;
            }
        });
    }

    private static Object processTransaction(String accountId, BalanceRequestPojo transferRequest, boolean isWithdraw) {
        Account account =  JooqConfiguration.getAccountDao().fetchOneById(accountId);
        if (account == null) {
            ResponsePojo response = ResponsePojo.REQUEST_ERROR_ACCOUNT_NOT_FOUND(accountId);
            LOGGER.error(response.getDeveloperMessage());
            return response;
        }

        if (!canWithdrawAmount(account, transferRequest.getAmount())) {
            ResponsePojo response = ResponsePojo.REQUEST_ERROR_NO_FUNDS(accountId, transferRequest.getAmount());
            LOGGER.error(response.getDeveloperMessage());
            return response;
        }

        Operation operation = new Operation(null, accountId, Timestamp.valueOf(LocalDateTime.now()), transferRequest.getAmount(), (byte) (isWithdraw ? 1 : 0));
        if (isWithdraw)
            operation.setAmount(operation.getAmount() * (-1));
        JooqConfiguration.getOperationDao().insert(operation);
        LOGGER.info(String.format("%s %d %s from account: ID %s",
                (isWithdraw ? "Withdrawn " : "Deposited "),
                transferRequest.getAmount(),
                Currency.getById(account.getCurrency().intValue()),
                accountId));
        return operation;
    }

    private static boolean canWithdrawAmount(Account account, Integer amount) {
        BigDecimal bdDeposits = JooqConfiguration.getDslContext()
                .select(sum(com.revolut.test.backend.accountWs.database.tables.Operation.OPERATION.AMOUNT))
                .from(com.revolut.test.backend.accountWs.database.tables.Operation.OPERATION)
                .where(com.revolut.test.backend.accountWs.database.tables.Operation.OPERATION.AMOUNT.greaterThan(0))
                .fetchOneInto(BigDecimal.class);
        BigDecimal bdWithdrawals = JooqConfiguration.getDslContext()
                .select(sum(com.revolut.test.backend.accountWs.database.tables.Operation.OPERATION.AMOUNT))
                .from(com.revolut.test.backend.accountWs.database.tables.Operation.OPERATION)
                .where(com.revolut.test.backend.accountWs.database.tables.Operation.OPERATION.AMOUNT.lessThan(0))
                .fetchOneInto(BigDecimal.class);
        int deposits = (bdDeposits == null) ? 0 : bdDeposits.toBigInteger().intValue();
        int withdrawals = (bdWithdrawals == null) ? 0 : bdWithdrawals.toBigInteger().intValue();
        return (account.getBalance() + deposits + withdrawals - amount) > (Integer.parseInt(Environment.get(Environment.BALANCE_MAX_NEGATIVE)) * (-1));
    }
}
