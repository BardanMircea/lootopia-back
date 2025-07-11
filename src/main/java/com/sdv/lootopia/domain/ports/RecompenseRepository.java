package com.sdv.lootopia.domain.ports;

import com.sdv.lootopia.domain.model.Recompense;

import java.util.List;
import java.util.Optional;

public interface RecompenseRepository {
    List<Recompense> findAll();

    Optional<Recompense> findById(Long id);

    Recompense save(Recompense r);
}

