package com.sdv.lootopia.infrastructure.repository;

import com.sdv.lootopia.domain.model.TransactionCouronnes;
import com.sdv.lootopia.domain.ports.TransactionCouronnesRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TransactionRepositoryAdapter implements TransactionCouronnesRepository {

    private final JpaTransactionRepository jpa;

    public TransactionRepositoryAdapter(JpaTransactionRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public List<TransactionCouronnes> findAll() {
        return jpa.findAll();
    }

    @Override
    public Optional<TransactionCouronnes> findById(Long id) {
        return jpa.findById(id);
    }

    @Override
    public TransactionCouronnes save(TransactionCouronnes t) {
        return jpa.save(t);
    }

    @Override
    public void deleteAll(List<TransactionCouronnes> transactions) {
        jpa.deleteAll();
    }
}
