package me.study.spring_boot_get_start.account;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoAccountRepository extends MongoRepository<MongoAccount, String> {
    Optional<MongoAccount> findByEmail(String email);
}
