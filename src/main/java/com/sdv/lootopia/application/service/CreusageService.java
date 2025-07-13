package com.sdv.lootopia.application.service;

import com.sdv.lootopia.domain.model.Creusage;
import com.sdv.lootopia.domain.ports.CreusageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreusageService {
    private final CreusageRepository repository;

    public List<Creusage> getAll() { return repository.findAll(); }
    public Optional<Creusage> getById(Long id) { return repository.findById(id); }
    public Creusage save(Creusage creusage) { return repository.save(creusage); }
}