package com.revolut.test.backend.accountWs.model;

public class ResponsePojo {
    private String developerMessage;
    private String userMessage;

    public ResponsePojo(String developerMessage, String userMessage) {
        this.developerMessage = developerMessage;
        this.userMessage = userMessage;
    }

    public static ResponsePojo REQUEST_ERROR_ACCOUNT_NOT_FOUND(String id) {
        return new ResponsePojo("Could not retrieve account with ID " + id,
                "It seems your account does not exist. Please, contact your account's manager");
    }

    public static ResponsePojo REQUEST_ERROR_INVALID_AMOUNT_FORMAT() {
        return new ResponsePojo("Value \"amount\" is in invalid format, an Integer was expected",
                "Something wrong happened during this operation. Please, contact a support agent");
    }

    public static ResponsePojo REQUEST_ERROR_NO_FUNDS(String accountId, Integer amount) {
        return new ResponsePojo("Account has no funds for withdrawal: ID " + accountId + ", amount " + amount,
                "Sorry, but your account has no funds for this transaction");
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    @Override
    public String toString() {
        return "ResponsePojo{" +
                "developerMessage='" + developerMessage + '\'' +
                ", userMessage='" + userMessage + '\'' +
                '}';
    }
}
