package com.sdv.lootopia.web.controller;
import com.sdv.lootopia.application.service.ParticipationService;
import com.sdv.lootopia.domain.model.Participation;
import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.web.dto.ParticipationRequestDTO;
import com.sdv.lootopia.web.dto.ParticipationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/participations")
@RequiredArgsConstructor
public class ParticipationController {

    private final ParticipationService participationService;

    @PutMapping("/{id}/annuler")
    public ResponseEntity<?> cancelParticipation(
            @PathVariable Long id,
            @AuthenticationPrincipal(expression = "utilisateur") Utilisateur utilisateur
    ) {
        boolean success = participationService.cancelParticipation(id, utilisateur.getId());

        if (!success) {
            return ResponseEntity.badRequest().body(Map.of("message","Impossible d'annuler cette participation."));
        }

        return ResponseEntity.ok(Map.of("message","Participation annulée avec succès."));
    }


    @GetMapping("/moi")
    public ResponseEntity<Object> getMyParticipations(
            @AuthenticationPrincipal(expression = "utilisateur") Utilisateur utilisateur
    ) {
        List<ParticipationResponseDTO> participations = participationService
                .getActiveOrFinishedParticipationsForUtilisateur(utilisateur.getId());
        try {
            return ResponseEntity.ok(participations);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping
    public ResponseEntity<Object> participate(@RequestBody ParticipationRequestDTO dto,
                                                                @AuthenticationPrincipal(expression = "utilisateur") Utilisateur utilisateur) {
        try {
            return ResponseEntity.ok(participationService.participateToChasse(utilisateur, dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Participation> getAll() { return participationService.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Participation> getById(@PathVariable Long id) {
        return participationService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
