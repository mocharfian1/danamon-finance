package com.danamon.finance.repositories;

import com.danamon.finance.models.RekInfo;
import com.danamon.finance.models.entities.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RekeningInformationRepository extends JpaRepository<RekInfo, Long> {
    @Query(
            value = "select r.id, a.id account_id, a.username, r.nomor_rekening, b.balance, b.updated_at as last_updated from rekening r join balance b on r.id = b.rekening_id join \"account\" a on r.account_id = a.id where r.nomor_rekening = :no_rek",
            nativeQuery = true)
    RekInfo findRekInfoByNomor_rekening(@Param("no_rek") String no_rek);

    @Query(
            value = "select r.id, a.id account_id, a.username, r.nomor_rekening, b.balance, b.updated_at as last_updated from rekening r join balance b on r.id = b.rekening_id join \"account\" a on r.account_id = a.id where r.account_id = :account_id",
            nativeQuery = true)
    List<RekInfo> findRekInfoByAccount_id(@Param("account_id") long account_id);
}
