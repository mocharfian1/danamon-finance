package com.danamon.finance.controllers;

import com.danamon.finance.models.RekInfo;
import com.danamon.finance.models.ResponseApiModel;
import com.danamon.finance.models.SendBalanceResponseModel;
import com.danamon.finance.models.entities.Account;
import com.danamon.finance.models.entities.Session;
import com.danamon.finance.models.request.LoginRequestBody;
import com.danamon.finance.models.request.SendBalanceRequestBody;
import com.danamon.finance.services.AccountService;
import com.danamon.finance.services.SessionService;
import com.danamon.finance.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trx")
public class TransactionController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    HttpHeaders responseHeaders = new HttpHeaders();

    @PostMapping(value = "/check_by_rek_number/{rekNumber}")
    public ResponseEntity<ResponseApiModel<RekInfo>> info(@RequestHeader("x-token") String currentToken,
                                                          @PathVariable(value = "rekNumber") String rekNumber){

        try{
            Session newToken = sessionService.generateSession(currentToken);
            if(newToken != null){
                responseHeaders.set("x-token", newToken.getToken());

                RekInfo getDetailsUserSend = accountService.checkByRekNumber(rekNumber);

                return ResponseEntity.ok().headers(responseHeaders).body(new ResponseApiModel<RekInfo>(
                        HttpStatus.OK.value(), "Success get information", getDetailsUserSend
                ));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ResponseApiModel<RekInfo>(HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED", null )
            );
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(responseHeaders).body(
                    new ResponseApiModel<RekInfo>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", null )
            );
        }
    }

    @PostMapping(value = "/send")
    public ResponseEntity<ResponseApiModel<SendBalanceResponseModel>> sendBalance(@RequestHeader("x-token") String currentToken,
                                                                                  @RequestBody SendBalanceRequestBody body){

        try{
            Session newToken = sessionService.generateSession(currentToken);
            if(newToken != null){
                responseHeaders.set("x-token", newToken.getToken());

                SendBalanceResponseModel trxSendBalance = transactionService.trxBalance(newToken, body);

                return ResponseEntity.status(trxSendBalance.getCodeResponse()).headers(responseHeaders).body(new ResponseApiModel<SendBalanceResponseModel>(
                        trxSendBalance.getCodeResponse(), trxSendBalance.getMessageResponse(),
                        trxSendBalance
                ));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ResponseApiModel<SendBalanceResponseModel>(HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED", null )
            );
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(responseHeaders).body(
                    new ResponseApiModel<SendBalanceResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", null )
            );
        }
    }
}
