package com.danamon.finance.services;

import com.danamon.finance.models.LoginResponseModel;
import com.danamon.finance.models.entities.Account;
import com.danamon.finance.models.entities.Balance;
import com.danamon.finance.models.entities.Rekening;
import com.danamon.finance.models.entities.Session;
import com.danamon.finance.repositories.AccountRepository;
import com.danamon.finance.repositories.BalanceRepository;
import com.danamon.finance.repositories.RekeningRepository;
import com.danamon.finance.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private RekeningRepository rekeningRepository;

    public LoginResponseModel login(String username, String password){

        Account account = accountRepository.findAccountByUsernameAndPassword(username, password);
        if(account != null){
            LoginResponseModel loginResponseModel = new LoginResponseModel();
            Session session = saveSession(account);

            loginResponseModel.setAccountId(session.getAccount_id());
            loginResponseModel.setUsername(session.getUsername());
            loginResponseModel.setToken(session.getToken());
            loginResponseModel.setExpired(session.getExpired());

            return loginResponseModel;
        }
        return null;
    }

    public Account register(String username, String password){
        Account newAccount = new Account();
        newAccount.setUsername(username);
        newAccount.setPassword(password);

        Account profil = accountRepository.save(newAccount);

        privateRekening(profil);


        return profil;
    }

    private void privateRekening(Account account){
        Rekening rekening = new Rekening();
        rekening.setNomor_rekening(String.valueOf(new Date().getTime() / 1000));
        rekening.setAccount_id(account.getId());
        rekening.setPin(123456);

        Rekening rek = rekeningRepository.save(rekening);
        privateBalance(rek, account);
    }

    private void privateBalance(Rekening rekening, Account account){
        Balance balance = new Balance();
        balance.setAccount_id(account.getId());
        balance.setRekening_id(rekening.getId());
        balance.setUsername(account.getUsername());
        balance.setBalance(0L);
        balance.setUpdated_at(String.valueOf(new Date().getTime() / 1000));

        balanceRepository.save(balance);
    }

    public Account info(long id){
        Account account = accountRepository.findOneById(id);
        account.setPassword(null);

        return account;
    }

    private Session saveSession(Account account){
        Session session = new Session();
        session.setAccount_id(account.getId());
        session.setUsername(account.getUsername());
        long timestamp = new Date().getTime() / 1000;
        session.setToken(Base64.getEncoder().encodeToString((session.getUsername() + timestamp).getBytes()));
        session.setCreated_at(String.valueOf(timestamp));
        session.setExpired(String.valueOf(timestamp + 3600));
        session.setUsed(false);

        return sessionRepository.save(session);
    }
}
