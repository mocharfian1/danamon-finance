package com.danamon.finance.controllers;

import com.danamon.finance.models.AccountInfoModel;
import com.danamon.finance.models.LoginResponseModel;
import com.danamon.finance.models.ResponseApiModel;
import com.danamon.finance.models.entities.Session;
import com.danamon.finance.models.request.LoginRequestBody;
import com.danamon.finance.models.entities.Account;
import com.danamon.finance.models.request.RegisterRequestBody;
import com.danamon.finance.services.AccountService;
import com.danamon.finance.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private SessionService sessionService;

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseApiModel<LoginResponseModel>> login(@RequestBody LoginRequestBody loginRequestBody){
        try{
            LoginResponseModel getLoginAccount = accountService.login(loginRequestBody.getUsername().toLowerCase(), loginRequestBody.getPassword());
            return ResponseEntity.ok(new ResponseApiModel<LoginResponseModel>(
                    HttpStatus.OK.value(), "Success get information", getLoginAccount
            ));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseApiModel<LoginResponseModel>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", null )
            );
        }

    }

    @PostMapping(value = "/register")
    public ResponseEntity<ResponseApiModel<Account>> register(@RequestBody RegisterRequestBody registerRequestBody){
        try{
            Account registerAccount = accountService.register(registerRequestBody.getUsername().toLowerCase(), registerRequestBody.getPassword());
            return ResponseEntity.ok(new ResponseApiModel<Account>(
                    HttpStatus.OK.value(), "Success register user", registerAccount
            ));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseApiModel<Account>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", null )
            );
        }

    }

    @PostMapping(value = "/info")
    public ResponseEntity<ResponseApiModel<AccountInfoModel>> info(@RequestHeader("x-token") String currentToken){
        try{
            Session newToken = sessionService.generateSession(currentToken);
            if(newToken != null){
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("x-token", newToken.getToken());

                AccountInfoModel getLoginAccount = accountService.info(newToken.getAccount_id());
                return ResponseEntity.ok().headers(responseHeaders).body(new ResponseApiModel<AccountInfoModel>(
                        HttpStatus.OK.value(), "Success get information", getLoginAccount
                ));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ResponseApiModel<AccountInfoModel>(HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED", null )
            );
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseApiModel<AccountInfoModel>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", null )
            );
        }
    }
}
