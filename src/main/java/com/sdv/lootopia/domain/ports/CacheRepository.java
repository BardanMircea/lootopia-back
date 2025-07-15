package com.sdv.lootopia.domain.ports;

import com.sdv.lootopia.domain.model.Cache;

import java.util.List;
import java.util.Optional;

public interface CacheRepository {
    List<Cache> findAll();

    Optional<Cache> findById(Long id);

    Cache save(Cache r);
}

