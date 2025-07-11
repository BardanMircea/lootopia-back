package com.sdv.lootopia.infrastructure.repository;

import com.sdv.lootopia.domain.model.Recompense;
import com.sdv.lootopia.domain.ports.RecompenseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRecompenseRepository extends JpaRepository<Recompense, Long>, RecompenseRepository {}

