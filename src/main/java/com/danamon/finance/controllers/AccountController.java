package com.danamon.finance.controllers;

import com.danamon.finance.models.LoginResponseModel;
import com.danamon.finance.models.request.LoginRequestBody;
import com.danamon.finance.models.entities.Account;
import com.danamon.finance.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseModel> login(@RequestBody LoginRequestBody loginRequestBody){
        LoginResponseModel getLoginAccount = accountService.login(loginRequestBody.getUsername().toLowerCase(), loginRequestBody.getPassword());
        return ResponseEntity.ok(getLoginAccount);
    }
}
