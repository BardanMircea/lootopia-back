package com.sdv.lootopia.application.service;

import com.sdv.lootopia.domain.model.TransactionCouronnes;
import com.sdv.lootopia.domain.ports.TransactionCouronnesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionCouronnesRepository transactionRepository;

    public TransactionService(TransactionCouronnesRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionCouronnes> getAll() { return transactionRepository.findAll(); }
    public Optional<TransactionCouronnes> getById(Long id) { return transactionRepository.findById(id); }
    public TransactionCouronnes save(TransactionCouronnes t) { return transactionRepository.save(t); }
}
