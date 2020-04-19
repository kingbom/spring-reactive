package com.kingbom.spring.reactive.repository;

import com.kingbom.spring.reactive.domain.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {
}
