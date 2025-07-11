package com.sdv.lootopia.domain.ports;

import com.sdv.lootopia.domain.model.TransactionCouronnes;

import java.util.List;
import java.util.Optional;

public interface TransactionCouronnesRepository {
    List<TransactionCouronnes> findAll();

    Optional<TransactionCouronnes> findById(Long id);

    TransactionCouronnes save(TransactionCouronnes t);
}
