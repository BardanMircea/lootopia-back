package com.sdv.lootopia.web.controller;

import com.sdv.lootopia.application.service.CreusageService;
import com.sdv.lootopia.domain.model.Creusage;
import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.web.dto.CreusageRequestDTO;
import com.sdv.lootopia.web.dto.CreusageResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creusages")
@RequiredArgsConstructor
public class CreusageController {

    private final CreusageService creusageService;

    @PostMapping
    public ResponseEntity<CreusageResponseDTO> creuser(
            @RequestBody CreusageRequestDTO dto,
            @AuthenticationPrincipal(expression = "utilisateur") Utilisateur utilisateur) {

        CreusageResponseDTO result = creusageService.tenterCreusage(dto, utilisateur);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public List<Creusage> getAll() { return creusageService.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Creusage> getById(@PathVariable Long id) {
        return creusageService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
