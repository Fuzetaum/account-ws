package com.revolut.test.backend.accountWs.system.datatables;

import com.revolut.test.backend.accountWs.database.tables.pojos.Account;
import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;
import org.jooq.types.UInteger;

import java.sql.Date;
import java.util.Locale;
import java.util.Map;

@SuppressWarnings("unused")
public class AccountTypeRegistry implements TypeRegistryConfigurer {
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
                    Date balanceLastUpdate = Date.valueOf(row.get("balance_last_update"));
                    return new Account(id, ownerName, currency, balance, balanceLastUpdate);
                }));
    }
}
