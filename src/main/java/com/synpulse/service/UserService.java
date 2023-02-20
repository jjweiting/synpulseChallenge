package com.synpulse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synpulse.repository.UserRepository;
import com.synpulse.model.User;

@Service
public class UserService {
  @Autowired
  private UserRepository repository;

  public User createUser(User u) {
    return repository.insert(u);
  }
}