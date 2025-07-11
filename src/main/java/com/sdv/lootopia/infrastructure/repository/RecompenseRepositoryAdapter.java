package com.sdv.lootopia.infrastructure.repository;

import com.sdv.lootopia.domain.model.Recompense;
import com.sdv.lootopia.domain.ports.RecompenseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RecompenseRepositoryAdapter implements RecompenseRepository {

    private final JpaRecompenseRepository jpa;

    public RecompenseRepositoryAdapter(JpaRecompenseRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public List<Recompense> findAll() {
        return jpa.findAll();
    }

    @Override
    public Optional<Recompense> findById(Long id) {
        return jpa.findById(id);
    }

    @Override
    public Recompense save(Recompense r) {
        return jpa.save(r);
    }
}
