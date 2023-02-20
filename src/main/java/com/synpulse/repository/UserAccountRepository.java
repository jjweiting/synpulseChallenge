package com.synpulse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.synpulse.model.*;

@Repository
@Component
public interface UserAccountRepository extends MongoRepository<UserAccount, String> {
}