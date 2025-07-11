package com.sdv.lootopia.infrastructure.repository;


import com.sdv.lootopia.domain.model.Chasse;
import com.sdv.lootopia.domain.ports.ChasseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaChasseRepository extends JpaRepository<Chasse, Long>, ChasseRepository {}
