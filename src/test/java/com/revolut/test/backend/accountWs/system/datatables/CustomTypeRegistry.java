package com.revolut.test.backend.accountWs.system.datatables;

import com.revolut.test.backend.accountWs.database.tables.pojos.Account;
import com.revolut.test.backend.accountWs.database.tables.pojos.Operation;
import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;
import org.jooq.types.UInteger;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

@SuppressWarnings("unused")
public class CustomTypeRegistry implements TypeRegistryConfigurer {
    @Override
    public Locale locale() {
        return Locale.ENGLISH;
    }

    @Override
    public void configureTypeRegistry(TypeRegistry typeRegistry) {
        typeRegistry.defineDataTableType(new DataTableType(Account.class,
                (Map<String, String> row) -> {
                    String id = row.get("id");
                    String ownerName = row.get("owner_name");
                    UInteger currency = UInteger.valueOf(row.get("currency"));
                    Integer balance = Integer.parseInt(row.get("balance"));
                    String tableLastUpdate = row.get("balance_last_update");
                    Date balanceLastUpdate;
                    if (tableLastUpdate.contains("today"))
                        balanceLastUpdate = new Date(Calendar.getInstance().getTime().getTime());
                    else
                        balanceLastUpdate = Date.valueOf(tableLastUpdate);
                    return new Account(id, ownerName, currency, balance, balanceLastUpdate);
                }));

        typeRegistry.defineDataTableType(new DataTableType(Operation.class,
                (Map<String, String> row) -> {
                    Integer id = Integer.parseInt(row.get("id"));
                    String accountId = row.get("account_id");
                    Timestamp processingDate = Timestamp.valueOf(row.get("processing_date"));
                    Integer amount = Integer.parseInt(row.get("amount"));
                    byte isWithdraw = (byte) ((row.get("is_withdraw").equalsIgnoreCase("true")) ? 1 : 0);
                    return new Operation(id, accountId, processingDate, amount, isWithdraw);
                }));
    }
}
