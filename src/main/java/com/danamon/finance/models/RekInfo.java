package com.danamon.finance.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RekInfo {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "account_id")
    private long account_id;

    @Column(name = "username")
    private String username;

    @Column(name = "nomor_rekening")
    private String nomor_rekening;

    @Column(name = "balance")
    private double balance;

    @Column(name = "last_updated")
    private long last_updated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNomor_rekening() {
        return nomor_rekening;
    }

    public void setNomor_rekening(String nomor_rekening) {
        this.nomor_rekening = nomor_rekening;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getLast_update() {
        return last_updated;
    }

    public void setLast_update(long last_update) {
        this.last_updated = last_update;
    }
}
