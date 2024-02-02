package com.danamon.finance.services;

import com.danamon.finance.models.LoginResponseModel;
import com.danamon.finance.models.entities.Account;
import com.danamon.finance.models.entities.Session;
import com.danamon.finance.repositories.AccountRepository;
import com.danamon.finance.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public LoginResponseModel login(String username, String password){

        Account account = accountRepository.findAccountByUsernameAndPassword(username, password);
        if(account != null){
            LoginResponseModel loginResponseModel = new LoginResponseModel();
            Session session = saveSession(account);

            loginResponseModel.setAccountId(session.getAccountId());
            loginResponseModel.setUsername(session.getUsername());
            loginResponseModel.setToken(session.getToken());
            loginResponseModel.setExpired(session.getExpired());

            return loginResponseModel;
        }
        return null;
    }

    private Session saveSession(Account account){
        Session session = new Session();
        session.setAccountId(account.getId());
        session.setUsername(account.getUsername());
        long timestamp = new Date().getTime() / 1000;
        System.out.println(timestamp);
        session.setToken(Base64.getEncoder().encodeToString((session.getUsername() + timestamp).getBytes()));
        session.setCreatedAt(String.valueOf(timestamp));
        session.setExpired(String.valueOf(timestamp + 3600));
        session.setUsed(true);

        return sessionRepository.save(session);
    }
}
