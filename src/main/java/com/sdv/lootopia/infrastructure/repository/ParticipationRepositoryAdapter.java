package com.sdv.lootopia.infrastructure.repository;
import com.sdv.lootopia.domain.model.Chasse;
import com.sdv.lootopia.domain.model.Participation;
import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.domain.ports.ParticipationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.sdv.lootopia.domain.model.Participation.Statut.ACTIF;
import static com.sdv.lootopia.domain.model.Participation.Statut.TERMINE;

@Repository
@RequiredArgsConstructor
public class ParticipationRepositoryAdapter implements ParticipationRepository {
    private final JpaParticipationRepository jpa;

    public List<Participation> findAll() { return jpa.findAll(); }
    public Optional<Participation> findById(Long id) { return jpa.findById(id); }
    public Participation save(Participation e) { return jpa.save(e); }

    @Override
    public Optional<Participation> findByUtilisateurIdAndChasseId(Long utilisateurId, Long chasseId) {
        return jpa.findByUtilisateurIdAndChasseId(utilisateurId, chasseId);
    }

    @Override
    public List<Participation> findByChasseId(Long chasseId) {
        return jpa.findByChasseId(chasseId);
    }

    @Override
    public List<Participation> findByUtilisateurIdAndStatut(Long utilisateurId) {
        return jpa.findByUtilisateurIdAndStatutIn(utilisateurId, List.of(ACTIF, TERMINE));
    }

    @Override
    public Optional<Participation> findByChasseAndUtilisateur(Chasse chasse, Utilisateur joueur) {
        return jpa.findByChasseAndUtilisateur(chasse, joueur);
    }
}

