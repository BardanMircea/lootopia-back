package com.sdv.lootopia.infrastructure.repository;

import com.sdv.lootopia.domain.model.Creusage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCreusageRepository extends JpaRepository<Creusage, Long> {}

