package com.sdv.lootopia.web.controller;

import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.application.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/by-email")
    public ResponseEntity<Utilisateur> getByEmail(@RequestParam String email) {
        Optional<Utilisateur> utilisateur = utilisateurService.getByEmail(email);
        return utilisateur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}