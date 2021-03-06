/*
 * This file is generated by jOOQ.
 */
package com.revolut.test.backend.accountWs.database.tables;


import com.revolut.test.backend.accountWs.database.DefaultSchema;
import com.revolut.test.backend.accountWs.database.Indexes;
import com.revolut.test.backend.accountWs.database.Keys;
import com.revolut.test.backend.accountWs.database.tables.records.OperationRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row5;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Operation extends TableImpl<OperationRecord> {

    private static final long serialVersionUID = 1885168302;

    /**
     * The reference instance of <code>operation</code>
     */
    public static final Operation OPERATION = new Operation();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OperationRecord> getRecordType() {
        return OperationRecord.class;
    }

    /**
     * The column <code>operation.id</code>.
     */
    public final TableField<OperationRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>operation.account_id</code>.
     */
    public final TableField<OperationRecord, String> ACCOUNT_ID = createField(DSL.name("account_id"), org.jooq.impl.SQLDataType.CHAR(32).nullable(false), this, "");

    /**
     * The column <code>operation.processing_date</code>.
     */
    public final TableField<OperationRecord, Timestamp> PROCESSING_DATE = createField(DSL.name("processing_date"), org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "");

    /**
     * The column <code>operation.amount</code>.
     */
    public final TableField<OperationRecord, Integer> AMOUNT = createField(DSL.name("amount"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>operation.is_withdraw</code>.
     */
    public final TableField<OperationRecord, Byte> IS_WITHDRAW = createField(DSL.name("is_withdraw"), org.jooq.impl.SQLDataType.TINYINT.nullable(false), this, "");

    /**
     * Create a <code>operation</code> table reference
     */
    public Operation() {
        this(DSL.name("operation"), null);
    }

    /**
     * Create an aliased <code>operation</code> table reference
     */
    public Operation(String alias) {
        this(DSL.name(alias), OPERATION);
    }

    /**
     * Create an aliased <code>operation</code> table reference
     */
    public Operation(Name alias) {
        this(alias, OPERATION);
    }

    private Operation(Name alias, Table<OperationRecord> aliased) {
        this(alias, aliased, null);
    }

    private Operation(Name alias, Table<OperationRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Operation(Table<O> child, ForeignKey<O, OperationRecord> key) {
        super(child, key, OPERATION);
    }

    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.OPERATION_ACCOUNT_ID, Indexes.OPERATION_PRIMARY);
    }

    @Override
    public Identity<OperationRecord, Integer> getIdentity() {
        return Keys.IDENTITY_OPERATION;
    }

    @Override
    public UniqueKey<OperationRecord> getPrimaryKey() {
        return Keys.KEY_OPERATION_PRIMARY;
    }

    @Override
    public List<UniqueKey<OperationRecord>> getKeys() {
        return Arrays.<UniqueKey<OperationRecord>>asList(Keys.KEY_OPERATION_PRIMARY);
    }

    @Override
    public List<ForeignKey<OperationRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<OperationRecord, ?>>asList(Keys.OPERATION_IBFK_1);
    }

    public Account account() {
        return new Account(this, Keys.OPERATION_IBFK_1);
    }

    @Override
    public Operation as(String alias) {
        return new Operation(DSL.name(alias), this);
    }

    @Override
    public Operation as(Name alias) {
        return new Operation(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Operation rename(String name) {
        return new Operation(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Operation rename(Name name) {
        return new Operation(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, String, Timestamp, Integer, Byte> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}
