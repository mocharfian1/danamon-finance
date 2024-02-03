package com.danamon.finance.services;

import com.danamon.finance.models.ResponseApiModel;
import com.danamon.finance.models.SendBalanceResponseModel;
import com.danamon.finance.models.entities.Account;
import com.danamon.finance.models.entities.Balance;
import com.danamon.finance.models.entities.Rekening;
import com.danamon.finance.models.entities.Session;
import com.danamon.finance.models.request.SendBalanceRequestBody;
import com.danamon.finance.repositories.AccountRepository;
import com.danamon.finance.repositories.BalanceRepository;
import com.danamon.finance.repositories.RekeningInformationRepository;
import com.danamon.finance.repositories.RekeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Value("${minimumSaldo}")
    private final long minimumSaldo = 0;

    @Autowired
    private RekeningInformationRepository rekeningInformationRepository;

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private RekeningRepository rekeningRepository;

    public SendBalanceResponseModel trxBalance(Session session, SendBalanceRequestBody body){
        long account_id = session.getAccount_id();
        rekeningInformationRepository.findRekInfoByAccount_id(account_id);

        Rekening fromRekening = rekeningRepository.findRekening(account_id, String.valueOf(body.getFromRekNumber()));
        Balance fromBalance = balanceRepository.findBalanceByAccount_idAndRekening_id(account_id, fromRekening.getId());

        if(fromBalance.getBalance() > minimumSaldo){
            return proccessTrx(session, body);
        }

        return new SendBalanceResponseModel(
                null,
                null,
                0,
                null,
                500,
                "Account Balance Limited"
        );
    }

    public SendBalanceResponseModel proccessTrx(Session session, SendBalanceRequestBody body){
        try {
            long account_id = session.getAccount_id();
            rekeningInformationRepository.findRekInfoByAccount_id(account_id);

            Rekening fromRekening = rekeningRepository.findRekening(account_id, String.valueOf(body.getFromRekNumber()));
            Balance fromBalance = balanceRepository.findBalanceByAccount_idAndRekening_id(account_id, fromRekening.getId());

            Rekening toRekening = rekeningRepository.findByNomorRekening(String.valueOf(body.getToRekNumber()));
            Balance toBalance = balanceRepository.findBalanceByAccount_idAndRekening_id(toRekening.getAccount_id(), toRekening.getId());

            fromBalance.setBalance(fromBalance.getBalance() - body.getNominal());
            toBalance.setBalance(toBalance.getBalance() + body.getNominal());

            balanceRepository.save(fromBalance);
            balanceRepository.save(toBalance);

            return new SendBalanceResponseModel(
                    fromRekening.getNomor_rekening(),
                    toRekening.getNomor_rekening(),
                    body.getNominal(),
                    body.getDescription(),
                    200,
                    "Send balance success"
            );
        } catch (Exception e){
            e.printStackTrace();
            return new SendBalanceResponseModel(
                    null,
                    null,
                    0,
                    null,
                    500,
                    "Send balance failed"
            );
        }
    }
}
