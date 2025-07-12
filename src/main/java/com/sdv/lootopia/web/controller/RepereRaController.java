package com.sdv.lootopia.web.controller;

import com.sdv.lootopia.application.service.RepereRaService;
import com.sdv.lootopia.domain.model.RepereRa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reperes")
public class RepereRaController {

    private final RepereRaService service;

    public RepereRaController(RepereRaService service) {
        this.service = service;
    }

    @GetMapping
    public List<RepereRa> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<RepereRa> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RepereRa create(@RequestBody RepereRa repereRa) {
        return service.save(repereRa);
    }
}

