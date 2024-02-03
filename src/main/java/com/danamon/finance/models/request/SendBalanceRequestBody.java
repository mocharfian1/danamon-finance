package com.danamon.finance.models.request;

public class SendBalanceRequestBody {
    private long fromRekNumber;
    private long toRekNumber;
    private long nominal;
    private String description;
    private String pin;

    public long getFromRekNumber() {
        return fromRekNumber;
    }

    public void setFromRekNumber(long fromRekNumber) {
        this.fromRekNumber = fromRekNumber;
    }

    public long getToRekNumber() {
        return toRekNumber;
    }

    public void setToRekNumber(long toRekNumber) {
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

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
