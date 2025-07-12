package com.sdv.lootopia.infrastructure.repository;

import com.sdv.lootopia.domain.model.Progression;
import com.sdv.lootopia.domain.ports.ProgressionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProgressionRepositoryAdapter implements ProgressionRepository {
    private final JpaProgressionRepository jpa;

    public ProgressionRepositoryAdapter(JpaProgressionRepository jpa) {
        this.jpa = jpa;
    }

    public List<Progression> findAll() { return jpa.findAll(); }
    public Optional<Progression> findById(Long id) { return jpa.findById(id); }
    public Progression save(Progression e) { return jpa.save(e); }
}
