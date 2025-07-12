package com.sdv.lootopia.domain.ports;
import com.sdv.lootopia.domain.model.Progression;

import java.util.List;
import java.util.Optional;

public interface ProgressionRepository {
    List<Progression> findAll();
    Optional<Progression> findById(Long id);
    Progression save(Progression progression);
}
