package com.danamon.finance.services;

import com.danamon.finance.models.LoginResponseModel;
import com.danamon.finance.models.entities.Account;
import com.danamon.finance.models.entities.Session;
import com.danamon.finance.repositories.AccountRepository;
import com.danamon.finance.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

@Service
public class SessionService {
    @Value("${expToken}")
    private int expToken;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public Session generateSession(String currentToken){
        Session currentSession = sessionRepository.findByToken(currentToken);
        if(currentSession != null){
            long currentTimestamp = new Date().getTime() / 1000;
            long expiredDateTime = Long.parseLong(currentSession.getExpired());
            boolean isUsed = currentSession.getUsed();

            if(currentTimestamp < expiredDateTime && !isUsed){
                Account resAccount = accountRepository.findOneById(currentSession.getAccount_id());
                sessionRepository.delete(currentSession);
                return saveSession(resAccount);
            }
        }

        return null;
    }

    private Session saveSession(Account account){
        Session session = new Session();
        session.setAccount_id(account.getId());
        session.setUsername(account.getUsername());
        long timestamp = new Date().getTime() / 1000;
        session.setToken(Base64.getEncoder().encodeToString((session.getUsername() + timestamp).getBytes()));
        session.setCreated_at(String.valueOf(timestamp));
        session.setExpired(String.valueOf(timestamp + expToken));
        session.setUsed(false);

        return sessionRepository.save(session);
    }
}
