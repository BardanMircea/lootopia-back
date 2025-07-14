package com.sdv.lootopia.domain.ports;
import com.sdv.lootopia.domain.model.Participation;

import java.util.List;
import java.util.Optional;

public interface ParticipationRepository {
    List<Participation> findAll();
    Optional<Participation> findById(Long id);
    Participation save(Participation participation);
    Optional<Participation> findByUtilisateurIdAndChasseId(Long utilisateurId, Long chasseId);
    List<Participation> findByChasseId(Long chasseId);
}
