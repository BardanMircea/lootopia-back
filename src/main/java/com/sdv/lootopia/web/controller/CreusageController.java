package com.sdv.lootopia.web.controller;

import com.sdv.lootopia.application.service.CreusageService;
import com.sdv.lootopia.domain.model.Creusage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creusages")
public class CreusageController {

    private final CreusageService service;

    public CreusageController(CreusageService service) {
        this.service = service;
    }

    @GetMapping
    public List<Creusage> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Creusage> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Creusage create(@RequestBody Creusage creusage) {
        return service.save(creusage);
    }
}
