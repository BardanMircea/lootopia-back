package com.sdv.lootopia.web.controller;

import com.sdv.lootopia.application.service.EtapeService;
import com.sdv.lootopia.domain.model.Etape;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etapes")
public class EtapeController {

    private final EtapeService service;

    public EtapeController(EtapeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Etape> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Etape> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Etape create(@RequestBody Etape etape) {
        return service.save(etape);
    }
}