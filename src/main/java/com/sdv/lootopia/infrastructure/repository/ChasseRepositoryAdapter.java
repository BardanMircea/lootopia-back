package com.sdv.lootopia.infrastructure.repository;

import com.sdv.lootopia.domain.model.Chasse;
import com.sdv.lootopia.domain.ports.ChasseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ChasseRepositoryAdapter implements ChasseRepository {

    private final JpaChasseRepository jpa;

    public ChasseRepositoryAdapter(JpaChasseRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public List<Chasse> findAll() { return jpa.findAll(); }

    @Override
    public Optional<Chasse> findById(Long id) { return jpa.findById(id); }

    @Override
    public Chasse save(Chasse chasse) { return jpa.save(chasse); }

    @Override
    public List<Chasse> findByOrganisateurId(Long utilisateurId) {
        return jpa.findByOrganisateurId(utilisateurId);
    }
}
