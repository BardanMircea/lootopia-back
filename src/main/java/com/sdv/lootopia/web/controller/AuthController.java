package com.sdv.lootopia.web.controller;

import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.infrastructure.repository.JpaUtilisateurRepository;
import com.sdv.lootopia.infrastructure.security.JwtService;
import com.sdv.lootopia.web.dto.AuthRequest;
import com.sdv.lootopia.web.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JpaUtilisateurRepository utilisateurRepo;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Utilisateur user) { // à faire: dto en lieu d'Utilisateur
        user.setMotDePasse(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode(user.getMotDePasse()));
        utilisateurRepo.save(user);
        return ResponseEntity.ok("Utilisateur enregistré avec succès !");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getMotDePasse()
                )
        );
        UserDetails user = (UserDetails) utilisateurRepo.findByEmail(authRequest.getEmail()).get();
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}