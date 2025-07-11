package com.sdv.lootopia.web.controller;

import com.sdv.lootopia.domain.model.Chasse;
import com.sdv.lootopia.application.service.ChasseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chasses")
@RequiredArgsConstructor
public class ChasseController {

    private final ChasseService chasseService;

    @GetMapping
    public ResponseEntity<List<Chasse>> getAllChasses() {
        return ResponseEntity.ok(chasseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chasse> getChasseById(@PathVariable Long id) {
        Optional<Chasse> chasse = chasseService.getById(id);
        return chasse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Chasse> createChasse(@RequestBody Chasse chasse) {
        Chasse saved = chasseService.save(chasse);
        return ResponseEntity.ok(saved);
    }
}
