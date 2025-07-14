package com.sdv.lootopia.domain.ports;

import com.sdv.lootopia.domain.model.Etape;

import java.util.List;
import java.util.Optional;

public interface EtapeRepository {
    List<Etape> findAll();
    Optional<Etape> findById(Long id);
    Etape save(Etape etape);
    List<Etape> findByChasseId(Long chasseId);
}
