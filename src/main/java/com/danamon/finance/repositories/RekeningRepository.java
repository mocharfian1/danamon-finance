package com.danamon.finance.repositories;

import com.danamon.finance.models.entities.Account;
import com.danamon.finance.models.entities.Balance;
import com.danamon.finance.models.entities.Rekening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RekeningRepository extends JpaRepository<Rekening, Long> {
    @Query(value = "select * from rekening where account_id = :account_id and nomor_rekening = :no_rek", nativeQuery = true)
    Rekening findRekening(@Param("account_id") long account_id, @Param("no_rek") String no_rek);

    @Query(value = "select * from rekening where nomor_rekening = :no_rek", nativeQuery = true)
    Rekening findByNomorRekening(@Param("no_rek") String no_rek);
}
