package com.revolut.test.backend.accountWs.model;

public class AccountRequestPojo {
    private String owner;
    private String currency;

    public AccountRequestPojo(String owner, String currency) {
        this.owner = owner;
        this.currency = currency;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "AccountRequestPojo{" +
                "ownerName='" + owner + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
