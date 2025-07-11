package com.sdv.lootopia.web.controller;

import com.sdv.lootopia.domain.model.Recompense;
import com.sdv.lootopia.application.service.RecompenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recompenses")
public class RecompenseController {

    private final RecompenseService recompenseService;

    public RecompenseController(RecompenseService recompenseService) {
        this.recompenseService = recompenseService;
    }

    @GetMapping
    public ResponseEntity<List<Recompense>> getAll() {
        return ResponseEntity.ok(recompenseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recompense> getById(@PathVariable Long id) {
        Optional<Recompense> r = recompenseService.getById(id);
        return r.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Recompense> create(@RequestBody Recompense r) {
        return ResponseEntity.ok(recompenseService.save(r));
    }
}
