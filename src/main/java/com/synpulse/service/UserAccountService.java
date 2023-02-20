package com.synpulse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synpulse.repository.UserAccountRepository;
import com.synpulse.model.UserAccount;

@Service
public class UserAccountService {
  @Autowired
  private UserAccountRepository repository;

  public UserAccount createUserAccount(UserAccount u) {
    return repository.insert(u);
  }
}