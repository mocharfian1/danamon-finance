package com.danamon.finance.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "session")
public class Session {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id")
    private Long account_id;

    @Column(name = "username")
    private String username;

    @Column(name = "referencetoken")
    private String referencetoken;

    @Column(name = "token")
    private String token;

    @Column(name = "created_at")
    private String created_at;

    @Column(name = "expired")
    private String expired;

    @Column(name = "used")
    private Boolean used;


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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReferencetoken() {
        return referencetoken;
    }

    public void setReferencetoken(String referencetoken) {
        this.referencetoken = referencetoken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }
}
