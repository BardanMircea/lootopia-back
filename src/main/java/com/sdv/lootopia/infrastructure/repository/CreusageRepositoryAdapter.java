package com.sdv.lootopia.infrastructure.repository;

import com.sdv.lootopia.domain.model.Creusage;
import com.sdv.lootopia.domain.ports.CreusageRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CreusageRepositoryAdapter implements CreusageRepository {
    private final JpaCreusageRepository jpa;

    public CreusageRepositoryAdapter(JpaCreusageRepository jpa) {
        this.jpa = jpa;
    }

    public List<Creusage> findAll() { return jpa.findAll(); }
    public Optional<Creusage> findById(Long id) { return jpa.findById(id); }
    public Creusage save(Creusage e) { return jpa.save(e); }
}

