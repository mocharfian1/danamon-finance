package com.danamon.finance.models;

public class SendBalanceResponseModel {
    private String fromRekNumber;
    private String toRekNumber;
    private long nominal;
    private String description;
    private int codeResponse;
    private String messageResponse;

    public SendBalanceResponseModel(String fromRekNumber, String toRekNumber, long nominal, String description, int codeResponse, String messageResponse) {
        this.fromRekNumber = fromRekNumber;
        this.toRekNumber = toRekNumber;
        this.nominal = nominal;
        this.description = description;
        this.codeResponse = codeResponse;
        this.messageResponse = messageResponse;
    }

    public String getFromRekNumber() {
        return fromRekNumber;
    }

    public void setFromRekNumber(String fromRekNumber) {
        this.fromRekNumber = fromRekNumber;
    }

    public String getToRekNumber() {
        return toRekNumber;
    }

    public void setToRekNumber(String toRekNumber) {
        this.toRekNumber = toRekNumber;
    }

    public long getNominal() {
        return nominal;
    }

    public void setNominal(long nominal) {
        this.nominal = nominal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCodeResponse() {
        return codeResponse;
    }

    public void setCodeResponse(int codeResponse) {
        this.codeResponse = codeResponse;
    }

    public String getMessageResponse() {
        return messageResponse;
    }

    public void setMessageResponse(String messageResponse) {
        this.messageResponse = messageResponse;
    }
}
