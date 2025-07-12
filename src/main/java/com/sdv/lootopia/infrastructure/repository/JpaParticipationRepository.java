package com.sdv.lootopia.infrastructure.repository;

import com.sdv.lootopia.domain.model.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaParticipationRepository extends JpaRepository<Participation, Long> {}

