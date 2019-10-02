package com.revolut.test.backend.accountWs.model;

public class BalanceRequestPojo {
    private Integer amount;

    public BalanceRequestPojo(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BalanceRequestPojo{" +
                "amount=" + amount +
                '}';
    }
}
