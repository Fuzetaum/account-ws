package com.revolut.test.backend.accountWs.configuration;

import com.revolut.test.backend.accountWs.database.tables.daos.AccountDao;
import com.revolut.test.backend.accountWs.database.tables.daos.OperationDao;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JooqConfiguration {
    private static AccountDao accountDao;
    private static OperationDao operationDao;
    private static DSLContext dslContext;

    public static AccountDao getAccountDao() { return accountDao; }
    public static OperationDao getOperationDao() { return operationDao; }

    public static DSLContext getDslContext() { return dslContext; }

    static void configure() {
        try {
            Connection connection = DriverManager.getConnection(Environment.get(Environment.DATABASE_URL),
                    Environment.get(Environment.DATABASE_USERNAME), Environment.get(Environment.DATABASE_PASSWORD));
            createDaos(connection);
            dslContext = DSL.using(connection, SQLDialect.MYSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createDaos(Connection connection) {
        Configuration config = new DefaultConfiguration().set(connection).set(SQLDialect.MYSQL);
        accountDao = new AccountDao(config);
        operationDao = new OperationDao(config);
    }
}
