package com.sdv.lootopia.infrastructure.repository;

import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.domain.ports.UtilisateurRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UtilisateurRepositoryAdapter implements UtilisateurRepository {

    private final JpaUtilisateurRepository jpa;

    public UtilisateurRepositoryAdapter(JpaUtilisateurRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Optional<Utilisateur> findByEmail(String email) {
        return jpa.findByEmail(email);
    }

    @Override
    public Utilisateur save(Utilisateur utilisateur) {
        return jpa.save(utilisateur);
    }

    @Override
    public Optional<Utilisateur> findByActivationToken(String token) {
        return jpa.findByActivationToken(token);
    }

    @Override
    public Integer deleteById(Long userId) {
        return jpa.deleteUtilisateurById(userId);
    }

    @Override
    public List<Utilisateur> getAll() {
        return jpa.findAll();
    }

    @Override
    public Optional<Utilisateur> findById(Long userId) {
        return jpa.findById(userId);
    }

    @Override
    public void delete(Utilisateur utilisateur) {
        jpa.delete(utilisateur);
    }
}
