package com.synpulse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synpulse.repository.TransactionRepository;
import com.synpulse.model.Transaction;

@Service
public class TransactionService {
  @Autowired
  private TransactionRepository repository;

  public Transaction createTransaction(Transaction u) {
    return repository.insert(u);
  }
}