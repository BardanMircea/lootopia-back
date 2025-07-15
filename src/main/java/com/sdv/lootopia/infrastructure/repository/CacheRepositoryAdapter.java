package com.sdv.lootopia.infrastructure.repository;

import com.sdv.lootopia.domain.model.Cache;
import com.sdv.lootopia.domain.ports.CacheRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CacheRepositoryAdapter implements CacheRepository {

    private final JpaCacheRepository jpa;

    public CacheRepositoryAdapter(JpaCacheRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public List<Cache> findAll() {
        return jpa.findAll();
    }

    @Override
    public Optional<Cache> findById(Long id) {
        return jpa.findById(id);
    }

    @Override
    public Cache save(Cache r) {
        return jpa.save(r);
    }
}
