package com.sdv.lootopia.web.controller;

import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.infrastructure.repository.JpaUtilisateurRepository;
import com.sdv.lootopia.infrastructure.security.JwtService;
import com.sdv.lootopia.infrastructure.security.UserPrincipal;
import com.sdv.lootopia.web.dto.AuthRequest;
import com.sdv.lootopia.web.dto.AuthResponse;
import com.sdv.lootopia.web.dto.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JpaUtilisateurRepository utilisateurRepo;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest registrationRequest) {
        Utilisateur user = new Utilisateur();
        user.setMotDePasse(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode(registrationRequest.getMotDePasse()));
        user.setEmail(registrationRequest.getEmail());
        user.setPseudo(registrationRequest.getPseudo());
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
        Utilisateur utilisateur = utilisateurRepo.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        UserDetails userDetails = new UserPrincipal(utilisateur);
        String token = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token));
    }
}