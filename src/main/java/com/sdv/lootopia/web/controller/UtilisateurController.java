package com.sdv.lootopia.web.controller;

import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.application.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @PostMapping("/register")
    public ResponseEntity<Utilisateur> register(@RequestBody Utilisateur utilisateur) {
        Utilisateur created = utilisateurService.enregistrer(utilisateur);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/by-email")
    public ResponseEntity<Utilisateur> getByEmail(@RequestParam String email) {
        Optional<Utilisateur> utilisateur = utilisateurService.getByEmail(email);
        return utilisateur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}