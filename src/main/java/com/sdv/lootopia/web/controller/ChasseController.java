package com.sdv.lootopia.web.controller;

import com.sdv.lootopia.domain.model.Chasse;
import com.sdv.lootopia.application.service.ChasseService;
import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.infrastructure.repository.JpaUtilisateurRepository;
import com.sdv.lootopia.infrastructure.security.UserPrincipal;
import com.sdv.lootopia.web.dto.ChasseApercuDTO;
import com.sdv.lootopia.web.dto.NouvelleChasseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chasses")
@RequiredArgsConstructor
public class ChasseController {

    private final ChasseService chasseService;
    private final JpaUtilisateurRepository utilisateurRepo;

    @GetMapping
    public List<Chasse> getAllChasses() {
        return chasseService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Chasse> getChasseById(@PathVariable Long id) {
        return chasseService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> createChasse(
            @Valid @RequestBody NouvelleChasseDTO dto,
            @AuthenticationPrincipal(expression = "utilisateur") Utilisateur utilisateur
    ) {
        // pour l'instant on se concentre sur les chasses carto
        if (!dto.getTypeMonde().equalsIgnoreCase("CARTOGRAPHIQUE")) {
            return ResponseEntity.badRequest().body("Seules les chasses cartographiques sont autorisées pour l'instant'.");
        }

        Chasse created = chasseService.createChasse(dto, utilisateur);
        return ResponseEntity.ok("Chasse créée avec succès. ID: " + created.getId());
    }

    @GetMapping("/mes-chasses")
    public ResponseEntity<List<ChasseApercuDTO>> getChassesCreatedByUtilisateur(
            @AuthenticationPrincipal UserPrincipal userPrincipal) {

        Utilisateur utilisateur = userPrincipal.getUtilisateur();
        List<ChasseApercuDTO> chasses = chasseService.getChassesCreatedByUtilisateur(utilisateur.getId());

        return ResponseEntity.ok(chasses);
    }
}

