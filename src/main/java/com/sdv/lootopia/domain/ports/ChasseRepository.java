package com.sdv.lootopia.domain.ports;

import com.sdv.lootopia.domain.model.Chasse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ChasseRepository {
    List<Chasse> findAll();
    Optional<Chasse> findById(Long id);
    Chasse save(Chasse chasse);
    List<Chasse> findByOrganisateurId(Long utilisateurId);
    Page<Chasse> findByVisibiliteAndStatut(Chasse.Visibilite visibilite, Chasse.Statut statut, Pageable pageable);

}
