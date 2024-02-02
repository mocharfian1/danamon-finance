package com.danamon.finance.repositories;

import com.danamon.finance.models.entities.Account;
import com.danamon.finance.models.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findByToken(String token);
}
