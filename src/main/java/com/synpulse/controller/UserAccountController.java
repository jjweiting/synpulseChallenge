package com.synpulse.controller;

import io.swagger.v3.oas.annotations.Operation;
import com.synpulse.model.*;

import org.iban4j.Iban;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.Serializable;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import com.synpulse.service.UserAccountService;

@RestController
@RequestMapping("/synpulse")
public class UserAccountController implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping("/useraccount")
    @Operation(summary = "create a useraccount api")
    public ResponseEntity<Object> UserV1(
            @RequestBody @Valid UserAccountRequest req,
            HttpServletResponse response) {
            
            if (req.getUserId() == null || req.getCountryCode() == null){
                return ResponseEntity.badRequest().body("invalid input");
            }

            // TODO check user exist
            // TODO check currency isValid
            // TODO currency transfer to country code
            
            req.countryCode = "TW";

            String currencyCode = Currency.getInstance(new Locale("", req.countryCode)).getCurrencyCode(); 
            logger.info("currencyCode: " + currencyCode);

            UUID userAccountId = UUID.randomUUID();
            Iban iban = new Iban.Builder()
                .buildRandom();
            
            UserAccount userAccount = new UserAccount(
                userAccountId,
                iban.toFormattedString(),
                req.userId,
                req.countryCode, 
                currencyCode, 
                null, 
                null);

            logger.info("iban = " + userAccount.iban);
            logger.info("userAccount = " + userAccount);

            userAccountService.createUserAccount(userAccount);

        return ResponseEntity.ok(iban);
    }
}
