package com.sdv.lootopia.domain.ports;

import com.sdv.lootopia.domain.model.Utilisateur;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository {
    Optional<Utilisateur> findByEmail(String email);
    Utilisateur save(Utilisateur utilisateur);
    Optional<Utilisateur> findByActivationToken(String token);

    Integer deleteById(Long userId);

    List<Utilisateur> getAll();
}
