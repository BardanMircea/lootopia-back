package com.sdv.lootopia.web.controller;

import com.sdv.lootopia.domain.model.Chasse;
import com.sdv.lootopia.application.service.ChasseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chasses")
public class ChasseController {

    private final ChasseService chasseService;

    public ChasseController(ChasseService chasseService) {
        this.chasseService = chasseService;
    }

    @GetMapping
    public List<Chasse> getAllChasses() {
        return chasseService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Chasse> getChasseById(@PathVariable Long id) {
        return chasseService.getById(id);
    }

    @PostMapping
    public Chasse createChasse(@RequestBody Chasse chasse) {
        return chasseService.save(chasse);
    }
}

