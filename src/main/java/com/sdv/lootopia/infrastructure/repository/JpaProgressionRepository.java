package com.sdv.lootopia.infrastructure.repository;
import com.sdv.lootopia.domain.model.Progression;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProgressionRepository extends JpaRepository<Progression, Long> {}

