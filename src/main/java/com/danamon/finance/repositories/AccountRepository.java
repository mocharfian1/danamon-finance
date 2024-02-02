package com.danamon.finance.repositories;

import com.danamon.finance.models.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByUsernameAndPassword(String username, String password);
    Account findOneById(long id);
}
