package com.sdv.lootopia.web.controller;
import com.sdv.lootopia.application.service.ProgressionService;
import com.sdv.lootopia.domain.model.Progression;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/progressions")
public class ProgressionController {

    private final ProgressionService service;

    public ProgressionController(ProgressionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Progression> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Progression> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Progression create(@RequestBody Progression progression) {
        return service.save(progression);
    }
}

