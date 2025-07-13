package com.sdv.lootopia.infrastructure.repository;


import com.sdv.lootopia.domain.model.Chasse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaChasseRepository extends JpaRepository<Chasse, Long> {
    List<Chasse> findByOrganisateurId(Long utilisateurId);
}
