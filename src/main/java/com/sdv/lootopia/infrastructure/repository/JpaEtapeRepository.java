package com.sdv.lootopia.infrastructure.repository;
import com.sdv.lootopia.domain.model.Etape;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEtapeRepository extends JpaRepository<Etape, Long> {}

