package com.sdv.lootopia.domain.ports;

import com.sdv.lootopia.domain.model.Creusage;

import java.util.List;
import java.util.Optional;

public interface CreusageRepository {
    List<Creusage> findAll();
    Optional<Creusage> findById(Long id);
    Creusage save(Creusage creusage);
}
