package com.sdv.lootopia.infrastructure.repository;

import com.sdv.lootopia.domain.model.RepereRa;
import com.sdv.lootopia.domain.ports.RepereRaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RepereRaRepositoryAdapter implements RepereRaRepository {
    private final JpaRepereRaRepository jpa;

    public RepereRaRepositoryAdapter(JpaRepereRaRepository jpa) {
        this.jpa = jpa;
    }

    public List<RepereRa> findAll() { return jpa.findAll(); }
    public Optional<RepereRa> findById(Long id) { return jpa.findById(id); }
    public RepereRa save(RepereRa e) { return jpa.save(e); }
}
