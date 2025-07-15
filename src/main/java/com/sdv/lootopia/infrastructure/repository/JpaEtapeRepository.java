package com.sdv.lootopia.infrastructure.repository;
import com.sdv.lootopia.domain.model.Etape;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaEtapeRepository extends JpaRepository<Etape, Long> {
    List<Etape> findByChasseId(Long chasseId);
}

