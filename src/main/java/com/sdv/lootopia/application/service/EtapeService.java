package com.sdv.lootopia.application.service;

import com.sdv.lootopia.domain.model.Etape;
import com.sdv.lootopia.domain.ports.EtapeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EtapeService {
    private final EtapeRepository repository;

    public List<Etape> getAll() { return repository.findAll(); }
    public Optional<Etape> getById(Long id) { return repository.findById(id); }
    public Etape save(Etape etape) { return repository.save(etape); }
}

