package com.sdv.lootopia.domain.ports;

import com.sdv.lootopia.domain.model.Chasse;
import java.util.List;
import java.util.Optional;

public interface ChasseRepository {
    List<Chasse> findAll();
    Optional<Chasse> findById(Long id);
    Chasse save(Chasse chasse);
    List<Chasse> findByOrganisateurId(Long utilisateurId);

}
