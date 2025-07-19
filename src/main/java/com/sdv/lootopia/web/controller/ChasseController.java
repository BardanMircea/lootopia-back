package com.sdv.lootopia.web.controller;

import com.sdv.lootopia.domain.model.Chasse;
import com.sdv.lootopia.application.service.ChasseService;
import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.infrastructure.security.UserPrincipal;
import com.sdv.lootopia.web.dto.ChasseResponseDTO;
import com.sdv.lootopia.web.dto.ChasseRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/chasses")
@RequiredArgsConstructor
public class ChasseController {

    private final ChasseService chasseService;

    @GetMapping
    public List<Chasse> getAllChasses() {
        return chasseService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getChasseById(@PathVariable Long id) {
        return ResponseEntity.ok(chasseService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>  updateChasse(@PathVariable Long id, @RequestBody @Valid ChasseRequestDTO chasseRequestDTO) {
        return ResponseEntity.ok(chasseService.updateChasse(id, chasseRequestDTO));
    }

    @PostMapping
    public ResponseEntity<?> createChasse(
            @Valid @RequestBody ChasseRequestDTO dto,
            @AuthenticationPrincipal(expression = "utilisateur") Utilisateur utilisateur
    ) {
        // pour l'instant on se concentre sur les chasses carto
        if (!dto.getMonde().equalsIgnoreCase("CARTOGRAPHIQUE")) {
            return ResponseEntity.badRequest().body(Map.of("message","Seules les chasses cartographiques sont autorisées pour l'instant'."));
        }

        ChasseResponseDTO created = chasseService.createChasse(dto, utilisateur);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/mes-chasses")
    public ResponseEntity<List<ChasseResponseDTO>> getChassesCreatedByUtilisateur(
            @AuthenticationPrincipal(expression = "utilisateur") Utilisateur utilisateur) {
        List<ChasseResponseDTO> chasses = chasseService.getChassesCreatedByUtilisateur(utilisateur.getId());

        return ResponseEntity.ok(chasses);
    }

    @GetMapping("/public")
    public ResponseEntity<Page<ChasseResponseDTO>> getChassesPubliquesEtActives(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Chasse> pageChasses = chasseService.getChassesPubliquesEtActives(pageable);
        Page<ChasseResponseDTO> dtoPage = pageChasses.map(ChasseResponseDTO::fromEntity);
        return ResponseEntity.ok(dtoPage);
    }
}

