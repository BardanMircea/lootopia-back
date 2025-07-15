package com.sdv.lootopia.web.controller;

import com.sdv.lootopia.domain.model.Cache;
import com.sdv.lootopia.application.service.CacheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/caches")
public class CacheController {

    private final CacheService cacheService;

    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @GetMapping
    public ResponseEntity<List<Cache>> getAll() {
        return ResponseEntity.ok(cacheService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cache> getById(@PathVariable Long id) {
        Optional<Cache> r = cacheService.getById(id);
        return r.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cache> create(@RequestBody Cache r) {
        return ResponseEntity.ok(cacheService.save(r));
    }
}
