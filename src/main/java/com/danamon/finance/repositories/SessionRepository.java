package com.danamon.finance.repositories;

import com.danamon.finance.models.entities.Account;
import com.danamon.finance.models.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
