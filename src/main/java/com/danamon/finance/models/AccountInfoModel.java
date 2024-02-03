package com.danamon.finance.models;

import com.danamon.finance.models.entities.Account;

import java.util.List;

public class AccountInfoModel {
    private Account account;
    private List<RekInfo> rekening;

    public AccountInfoModel(Account account, List<RekInfo> rekening) {
        this.account = account;
        this.rekening = rekening;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<RekInfo> getRekening() {
        return rekening;
    }

    public void setRekening(List<RekInfo> rekening) {
        this.rekening = rekening;
    }
}
