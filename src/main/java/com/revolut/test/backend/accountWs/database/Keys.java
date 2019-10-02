/*
 * This file is generated by jOOQ.
 */
package com.revolut.test.backend.accountWs.database;


import com.revolut.test.backend.accountWs.database.tables.Account;
import com.revolut.test.backend.accountWs.database.tables.FlywaySchemaHistory;
import com.revolut.test.backend.accountWs.database.tables.Operation;
import com.revolut.test.backend.accountWs.database.tables.records.AccountRecord;
import com.revolut.test.backend.accountWs.database.tables.records.FlywaySchemaHistoryRecord;
import com.revolut.test.backend.accountWs.database.tables.records.OperationRecord;

import javax.annotation.processing.Generated;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code></code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<OperationRecord, Integer> IDENTITY_OPERATION = Identities0.IDENTITY_OPERATION;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AccountRecord> KEY_ACCOUNT_PRIMARY = UniqueKeys0.KEY_ACCOUNT_PRIMARY;
    public static final UniqueKey<FlywaySchemaHistoryRecord> KEY_FLYWAY_SCHEMA_HISTORY_PRIMARY = UniqueKeys0.KEY_FLYWAY_SCHEMA_HISTORY_PRIMARY;
    public static final UniqueKey<OperationRecord> KEY_OPERATION_PRIMARY = UniqueKeys0.KEY_OPERATION_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<OperationRecord, AccountRecord> OPERATION_IBFK_1 = ForeignKeys0.OPERATION_IBFK_1;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<OperationRecord, Integer> IDENTITY_OPERATION = Internal.createIdentity(Operation.OPERATION, Operation.OPERATION.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<AccountRecord> KEY_ACCOUNT_PRIMARY = Internal.createUniqueKey(Account.ACCOUNT, "KEY_account_PRIMARY", Account.ACCOUNT.ID);
        public static final UniqueKey<FlywaySchemaHistoryRecord> KEY_FLYWAY_SCHEMA_HISTORY_PRIMARY = Internal.createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, "KEY_flyway_schema_history_PRIMARY", FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK);
        public static final UniqueKey<OperationRecord> KEY_OPERATION_PRIMARY = Internal.createUniqueKey(Operation.OPERATION, "KEY_operation_PRIMARY", Operation.OPERATION.ID);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<OperationRecord, AccountRecord> OPERATION_IBFK_1 = Internal.createForeignKey(com.revolut.test.backend.accountWs.database.Keys.KEY_ACCOUNT_PRIMARY, Operation.OPERATION, "operation_ibfk_1", Operation.OPERATION.ACCOUNT_ID);
    }
}
