package com.sdv.lootopia.application.service;

import com.sdv.lootopia.domain.model.Chasse;
import com.sdv.lootopia.domain.ports.ChasseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChasseService {
    private final ChasseRepository chasseRepository;

    public List<Chasse> getAll() { return chasseRepository.findAll(); }
    public Optional<Chasse> getById(Long id) { return chasseRepository.findById(id); }
    public Chasse save(Chasse chasse) { return chasseRepository.save(chasse); }
}

