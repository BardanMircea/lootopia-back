package com.sdv.lootopia.infrastructure.repository;

import com.sdv.lootopia.domain.model.Cache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCacheRepository extends JpaRepository<Cache, Long>{}

