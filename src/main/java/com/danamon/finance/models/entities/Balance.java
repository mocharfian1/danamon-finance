package com.danamon.finance.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "balance")
public class Balance {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "account_id")
    Long account_id;

    @Column(name = "rekening_id")
    Long rekening_id;

    @Column(name = "username")
    String username;

    @Column(name = "balance")
    Long balance;

    @Column(name = "updated_at")
    String updated_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public Long getRekening_id() {
        return rekening_id;
    }

    public void setRekening_id(Long rekening_id) {
        this.rekening_id = rekening_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
