package com.sdv.lootopia.infrastructure.repository;

import com.sdv.lootopia.domain.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUtilisateurRepository extends JpaRepository<Utilisateur, Long>{
    Optional<Utilisateur> findByEmail(String email);
}
