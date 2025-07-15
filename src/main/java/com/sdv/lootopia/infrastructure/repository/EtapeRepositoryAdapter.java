package com.sdv.lootopia.infrastructure.repository;

import com.sdv.lootopia.domain.model.Etape;
import com.sdv.lootopia.domain.ports.EtapeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EtapeRepositoryAdapter implements EtapeRepository {
    private final JpaEtapeRepository jpa;

    public EtapeRepositoryAdapter(JpaEtapeRepository jpa) {
        this.jpa = jpa;
    }

    public List<Etape> findAll() { return jpa.findAll(); }
    public Optional<Etape> findById(Long id) { return jpa.findById(id); }
    public Etape save(Etape e) { return jpa.save(e); }

    @Override
    public List<Etape> findByChasseId(Long chasseId) {
        return jpa.findByChasseId(chasseId);
    }
}

