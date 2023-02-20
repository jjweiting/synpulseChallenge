package com.synpulse.controller;

import io.swagger.v3.oas.annotations.Operation;
import com.synpulse.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import com.synpulse.service.TransactionService;

@RestController
@RequestMapping("/synpulse")
public class TransactionController implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transaction")
    @Operation(summary = "create a transaction api")
    public ResponseEntity<Object> UserV1(
            @RequestBody @Valid TransactionRequest req,
            HttpServletResponse response) {
            
            if (req.getPayeeIBAN() == null || req.getPayerIBAN() == null || req.getAmount() == 0){
                return ResponseEntity.badRequest().body("invalid input");
            }
            
            UUID uuid = UUID.randomUUID();
            logger.info("uuid " + uuid);
            Transaction transaction = new Transaction(uuid, req.getPayerIBAN(), req.getPayeeIBAN(), req.getAmount());
            
            logger.info("transactionId = " + transaction.id);
            transactionService.createTransaction(transaction);

        return ResponseEntity.ok(uuid);
    }
}
