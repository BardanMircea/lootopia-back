package com.sdv.lootopia.web.controller;

import com.sdv.lootopia.application.service.EtapeService;
import com.sdv.lootopia.domain.model.Chasse;
import com.sdv.lootopia.domain.model.Etape;
import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.web.dto.ChasseResponseDTO;
import com.sdv.lootopia.web.dto.EtapeRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etapes")
@RequiredArgsConstructor
public class EtapeController {

    private final EtapeService etapeService;

    @PostMapping
    public ResponseEntity<?> createEtape(@RequestBody @Valid EtapeRequestDTO dto,
                                         @AuthenticationPrincipal(expression = "utilisateur") Utilisateur utilisateur) {
        Chasse chasseMere = etapeService.ajouterEtape(dto, utilisateur);
        return ResponseEntity.ok(ChasseResponseDTO.fromEntity(chasseMere));
    }

    @GetMapping("/chasse/{chasseId}")
    public ResponseEntity<List<Etape>> listerParChasse(@PathVariable Long chasseId) {
        return ResponseEntity.ok(etapeService.listerEtapesParChasse(chasseId));
    }

    @GetMapping
    public List<Etape> getAll() { return etapeService.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Etape> getById(@PathVariable Long id) {
        return etapeService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}