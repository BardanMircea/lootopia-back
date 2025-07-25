package com.sdv.lootopia.infrastructure.repository;

import com.sdv.lootopia.domain.model.Chasse;
import com.sdv.lootopia.domain.model.Participation;
import com.sdv.lootopia.domain.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface JpaParticipationRepository extends JpaRepository<Participation, Long> {

    Optional<Participation> findByUtilisateurIdAndChasseId(Long utilisateurId, Long chasseId);
    List<Participation> findByChasseId(Long chasseId);
    List<Participation> findByUtilisateurIdAndStatutIn(Long utilisateurId, List<Participation.Statut> statuts);

    Optional<Participation> findByChasseAndUtilisateur(Chasse chasse, Utilisateur joueur);

}

