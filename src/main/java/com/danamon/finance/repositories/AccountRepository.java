package com.danamon.finance.repositories;

import com.danamon.finance.models.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByUsernameAndPassword(String username, String password);
}
