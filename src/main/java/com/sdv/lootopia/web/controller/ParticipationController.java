package com.sdv.lootopia.web.controller;
import com.sdv.lootopia.application.service.ParticipationService;
import com.sdv.lootopia.domain.model.Participation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participations")
public class ParticipationController {

    private final ParticipationService service;

    public ParticipationController(ParticipationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Participation> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Participation> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Participation create(@RequestBody Participation participation) {
        return service.save(participation);
    }
}
