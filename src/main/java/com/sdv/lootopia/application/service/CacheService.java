package com.sdv.lootopia.application.service;

import com.sdv.lootopia.domain.model.Cache;
import com.sdv.lootopia.domain.ports.CacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CacheService {
    private final CacheRepository cacheRepository;

    public List<Cache> getAll() { return cacheRepository.findAll(); }
    public Optional<Cache> getById(Long id) { return cacheRepository.findById(id); }
    public Cache save(Cache r) { return cacheRepository.save(r); }
}
