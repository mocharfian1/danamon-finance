package com.danamon.finance.repositories;

import com.danamon.finance.models.entities.Account;
import com.danamon.finance.models.entities.Rekening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RekeningRepository extends JpaRepository<Rekening, Long> {

}
