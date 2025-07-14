package com.sdv.lootopia.web.controller;
import com.sdv.lootopia.application.service.ParticipationService;
import com.sdv.lootopia.domain.model.Participation;
import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.infrastructure.security.UserPrincipal;
import com.sdv.lootopia.web.dto.ParticipationRequestDTO;
import com.sdv.lootopia.web.dto.ParticipationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participations")
@RequiredArgsConstructor
public class ParticipationController {

    private final ParticipationService participationService;

    @PostMapping
    public ResponseEntity<ParticipationResponseDTO> participate(@RequestBody ParticipationRequestDTO dto,
                                                                @AuthenticationPrincipal(expression = "utilisateur") Utilisateur utilisateur) {
        return ResponseEntity.ok(participationService.participateToChasse(utilisateur, dto));
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
