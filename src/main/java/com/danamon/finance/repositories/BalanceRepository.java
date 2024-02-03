package com.danamon.finance.repositories;

import com.danamon.finance.models.entities.Account;
import com.danamon.finance.models.entities.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
    @Query(value = "select * from balance where account_id = :account_id and rekening_id = :rekening_id", nativeQuery = true)
    Balance findBalanceByAccount_idAndRekening_id(@Param("account_id") long account_id, @Param("rekening_id") long rekening_id);
}
