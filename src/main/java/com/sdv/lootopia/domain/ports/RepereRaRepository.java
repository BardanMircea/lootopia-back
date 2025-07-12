package com.sdv.lootopia.domain.ports;

import com.sdv.lootopia.domain.model.RepereRa;

import java.util.List;
import java.util.Optional;

public interface RepereRaRepository {
    List<RepereRa> findAll();
    Optional<RepereRa> findById(Long id);
    RepereRa save(RepereRa repereRa);
}