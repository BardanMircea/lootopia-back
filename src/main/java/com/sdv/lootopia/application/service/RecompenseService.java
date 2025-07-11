package com.sdv.lootopia.application.service;

import com.sdv.lootopia.domain.model.Recompense;
import com.sdv.lootopia.domain.ports.RecompenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecompenseService {
    private final RecompenseRepository recompenseRepository;

    public List<Recompense> getAll() { return recompenseRepository.findAll(); }
    public Optional<Recompense> getById(Long id) { return recompenseRepository.findById(id); }
    public Recompense save(Recompense r) { return recompenseRepository.save(r); }
}
