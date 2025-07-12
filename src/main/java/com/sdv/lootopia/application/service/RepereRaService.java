package com.sdv.lootopia.application.service;

import com.sdv.lootopia.domain.model.RepereRa;
import com.sdv.lootopia.domain.ports.RepereRaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepereRaService {
    private final RepereRaRepository repository;

    public RepereRaService(RepereRaRepository repository) {
        this.repository = repository;
    }

    public List<RepereRa> getAll() { return repository.findAll(); }
    public Optional<RepereRa> getById(Long id) { return repository.findById(id); }
    public RepereRa save(RepereRa repere) { return repository.save(repere); }
}