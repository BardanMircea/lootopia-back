package com.sdv.lootopia.application.service;

import com.sdv.lootopia.domain.model.Progression;
import com.sdv.lootopia.domain.ports.ProgressionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgressionService {
    private final ProgressionRepository repository;

    public ProgressionService(ProgressionRepository repository) {
        this.repository = repository;
    }

    public List<Progression> getAll() { return repository.findAll(); }
    public Optional<Progression> getById(Long id) { return repository.findById(id); }
    public Progression save(Progression p) { return repository.save(p); }
}
