package com.revolut.test.backend.accountWs.service;

import com.revolut.test.backend.accountWs.configuration.JooqConfiguration;
import com.revolut.test.backend.accountWs.database.tables.pojos.Account;
import com.revolut.test.backend.accountWs.model.AccountPojo;
import com.revolut.test.backend.accountWs.model.AccountRequestPojo;
import com.revolut.test.backend.accountWs.model.Currency;
import com.revolut.test.backend.accountWs.model.ResponsePojo;
import com.revolut.test.backend.accountWs.utils.RequestUtils;
import org.jooq.types.UInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.Calendar;
import java.util.UUID;

public class AccountService {
    private static Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    public static void createAccount() {
        JavalinApp.post("/account", ctx -> {
            AccountRequestPojo accountRequest = (AccountRequestPojo) RequestUtils.fromJson(ctx.body(), AccountRequestPojo.class);
            Account account = new Account(UUID.randomUUID().toString(),
                    accountRequest.getOwner(),
                    UInteger.valueOf(Currency.getByCode(accountRequest.getCurrency()).getId()),
                    0,
                    new Date(Calendar.getInstance().getTime().getTime()));
            return false;
        });
    }

    public static void getAccount() {
        JavalinApp.get("/account/:id", ctx -> {
            String accountId = ctx.pathParam("id");
            Account account =  JooqConfiguration.getAccountDao().fetchOneById(accountId);
            if (account == null) {
                ResponsePojo response = ResponsePojo.REQUEST_ERROR_ACCOUNT_NOT_FOUND(accountId);
                LOGGER.error(response.getDeveloperMessage());
                return response;
            }
            return new AccountPojo(account.getId(),
                    account.getOwnerName(),
                    UInteger.valueOf(account.getBalance()),
                    Currency.getById(account.getCurrency().intValue()).getName());
        });
    }
}
