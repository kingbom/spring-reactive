package com.kingbom.spring.reactive.rest;

import com.kingbom.spring.reactive.domain.Transaction;
import com.kingbom.spring.reactive.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Lazy
@RestController
@RequiredArgsConstructor
public class TransactionResource {

    private final TransactionRepository transactionRepository;

    @PostMapping("/api/transactions")
    private Mono<Transaction> saveTransaction(@RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @GetMapping("/api/transactions")
    public Flux<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @GetMapping("/api/transactions/{id}")
    public Mono<Transaction> getTransaction(@PathVariable String id){
        return transactionRepository.findById(id);
    }
}
