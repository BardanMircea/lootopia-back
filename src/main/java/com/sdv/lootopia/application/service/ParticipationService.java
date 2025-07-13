package com.sdv.lootopia.application.service;

import com.sdv.lootopia.domain.model.Participation;
import com.sdv.lootopia.domain.ports.ParticipationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParticipationService {
    private final ParticipationRepository repository;

    public List<Participation> getAll() { return repository.findAll(); }
    public Optional<Participation> getById(Long id) { return repository.findById(id); }
    public Participation save(Participation p) { return repository.save(p); }
}