package com.revolut.test.backend.accountWs.job;

import com.revolut.test.backend.accountWs.configuration.JooqConfiguration;
import com.revolut.test.backend.accountWs.database.tables.Operation;
import com.revolut.test.backend.accountWs.database.tables.pojos.Account;
import com.revolut.test.backend.accountWs.model.JobRequestPojo;
import com.revolut.test.backend.accountWs.model.ResponsePojo;
import com.revolut.test.backend.accountWs.service.JavalinApp;
import com.revolut.test.backend.accountWs.utils.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import static com.revolut.test.backend.accountWs.database.tables.Account.ACCOUNT;
import static org.jooq.impl.DSL.*;

public class UpdateBalanceJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateBalanceJob.class);

    public static void updateBalances() {
        JavalinApp.post("/update-balances", ctx -> {
            JobRequestPojo requestPojo = (JobRequestPojo) RequestUtils.fromJson(ctx.body(), JobRequestPojo.class);
            LOGGER.info("Job execution started: replica #"+ requestPojo.getInstance());
            Integer accountsPerReplica = Math.round(JooqConfiguration.getDslContext()
                .selectCount()
                .from(ACCOUNT)
                .fetchOne(0, Integer.class) / requestPojo.getReplicas().floatValue());
            List<String> idList = JooqConfiguration.getDslContext()
                    .select(ACCOUNT.ID)
                    .from(ACCOUNT)
                    .limit(accountsPerReplica)
                    .offset(accountsPerReplica * (requestPojo.getInstance() - 1))
                    .fetch()
                    .getValues(ACCOUNT.ID, String.class);

            int accountsUpdated = 0;
            for (String id : idList) {
                Account account = JooqConfiguration.getAccountDao().fetchOneById(id);
                Integer balanceChange = getAccountBalanceChange(id);
                if (balanceChange != 0) {
                    account.setBalance(account.getBalance() + balanceChange);
                    account.setBalanceLastUpdate(new Date(Calendar.getInstance().getTime().getTime()));
                    JooqConfiguration.getAccountDao().update(account);
                    accountsUpdated += 1;
                }
            }
            LOGGER.info("Job execution ended");
            return new ResponsePojo("" + accountsUpdated, "Account balances updated: " + accountsUpdated);
        });
    }

    private static Integer getAccountBalanceChange(String id) {
        BigDecimal bdBalanceChange = JooqConfiguration.getDslContext()
                .select(sum(Operation.OPERATION.AMOUNT))
                .from(Operation.OPERATION)
                .join(ACCOUNT).on(Operation.OPERATION.ACCOUNT_ID.eq(ACCOUNT.ID))
                .where(Operation.OPERATION.ACCOUNT_ID.eq(id))
                .and(ACCOUNT.BALANCE_LAST_UPDATE.lessThan(date(Operation.OPERATION.PROCESSING_DATE)))
                .fetchOneInto(BigDecimal.class);
        return (bdBalanceChange == null) ? 0 : bdBalanceChange.toBigInteger().intValue();
    }
}
