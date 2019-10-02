package com.revolut.test.backend.accountWs.model;

public enum Currency {
    AUD(0, "AUD"),
    CAD(1, "CAD"),
    CNY(2, "CNY"),
    CZK(3, "CZK"),
    DKK(4, "DKK"),
    EUR(5, "EUR"),
    GBP(6, "GBP"),
    HUF(7, "HUF"),
    IDR(8, "IDR"),
    ILS(9, "ILS"),
    INR(10, "INR"),
    MXN(11, "MXN"),
    NOK(12, "NOK"),
    PLN(13, "PLN"),
    RON(14, "RON"),
    RUB(15, "RUB"),
    SEK(16, "SEK"),
    TRY(17, "TRY"),
    USD(18, "USD");

    private int id;
    private String name;

    Currency(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Currency getById(int id) throws IllegalArgumentException {
        switch (id) {
            case 0:
                return Currency.AUD;
            case 1:
                return Currency.CAD;
            case 2:
                return Currency.CNY;
            case 3:
                return Currency.CZK;
            case 4:
                return Currency.DKK;
            case 5:
                return Currency.EUR;
            case 6:
                return Currency.GBP;
            case 7:
                return Currency.HUF;
            case 8:
                return Currency.IDR;
            case 9:
                return Currency.ILS;
            case 10:
                return Currency.INR;
            case 11:
                return Currency.MXN;
            case 12:
                return Currency.NOK;
            case 13:
                return Currency.PLN;
            case 14:
                return Currency.RON;
            case 15:
                return Currency.RUB;
            case 16:
                return Currency.SEK;
            case 17:
                return Currency.TRY;
            case 18:
                return Currency.USD;
            default:
                throw new IllegalArgumentException("Currency with id " + id + " is not supported yet");
        }
    }

    public static Currency getByCode(String code) throws IllegalArgumentException {
        switch (code) {
            case "AUD":
                return Currency.AUD;
            case "CAD":
                return Currency.CAD;
            case "CNY":
                return Currency.CNY;
            case "CZK":
                return Currency.CZK;
            case "DKK":
                return Currency.DKK;
            case "EUR":
                return Currency.EUR;
            case "GBP":
                return Currency.GBP;
            case "HUF":
                return Currency.HUF;
            case "IDR":
                return Currency.IDR;
            case "ILS":
                return Currency.ILS;
            case "INR":
                return Currency.INR;
            case "MXN":
                return Currency.MXN;
            case "NOK":
                return Currency.NOK;
            case "PLN":
                return Currency.PLN;
            case "RON":
                return Currency.RON;
            case "RUB":
                return Currency.RUB;
            case "SEK":
                return Currency.SEK;
            case "TRY":
                return Currency.TRY;
            case "USD":
                return Currency.USD;
            default:
                throw new IllegalArgumentException("Currency with code " + code + " is not supported yet");
        }
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
