package com.sdv.lootopia.infrastructure.repository;

import com.sdv.lootopia.domain.model.TransactionCouronnes;
import com.sdv.lootopia.domain.ports.TransactionCouronnesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTransactionRepository extends JpaRepository<TransactionCouronnes, Long>, TransactionCouronnesRepository {}
