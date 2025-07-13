package com.sdv.lootopia.web.controller;

import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.infrastructure.email.EmailService;
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

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JpaUtilisateurRepository utilisateurRepo;
    private final JwtService jwtService;
    private final EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest registrationRequest) {
        Utilisateur user = new Utilisateur();
        user.setMotDePasse(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode(registrationRequest.getMotDePasse()));
        user.setEmail(registrationRequest.getEmail());
        user.setPseudo(registrationRequest.getPseudo());

        user.setActivationToken(UUID.randomUUID().toString());
        user.setActivationTokenExpiration(LocalDateTime.now().plusHours(24));
        user.setCompteActif(false);

        utilisateurRepo.save(user);

        emailService.sendActivationEmail(user.getEmail(), user.getActivationToken());

        utilisateurRepo.save(user);
        return ResponseEntity.ok("Lien d'activation envoyé avec succès !");
    }

    @GetMapping("/activate")
    public ResponseEntity<String> activate(@RequestParam String token) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepo.findByActivationToken(token);

        if (utilisateurOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Lien invalide");
        }

        Utilisateur utilisateur = utilisateurOpt.get();

        if (utilisateur.isCompteActif()) {
            return ResponseEntity.ok("Compte déjà activé !");
        }

        if (utilisateur.getActivationTokenExpiration().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Lien expiré. Demandez-en un nouveau.");
        }

        utilisateur.setCompteActif(true);
        utilisateur.setActivationToken(null);
        utilisateur.setActivationTokenExpiration(null);
        utilisateurRepo.save(utilisateur);

        return ResponseEntity.ok("Compte activé avec succès !");
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