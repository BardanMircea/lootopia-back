package com.sdv.lootopia.application.service;

import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.infrastructure.repository.JpaUtilisateurRepository;
import com.sdv.lootopia.infrastructure.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JpaUtilisateurRepository utilisateurRepository;
    private final AuthenticationManager authManager;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthService(JpaUtilisateurRepository utilisateurRepository, AuthenticationManager authManager,
                       PasswordEncoder encoder, JwtService jwtService) {
        this.utilisateurRepository = utilisateurRepository;
        this.authManager = authManager;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    public String authenticate(String email, String motDePasse) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, motDePasse)
        );
        User principal = (User) auth.getPrincipal();
        return jwtService.generateToken(principal);
    }

    public void register(Utilisateur utilisateur) {
        utilisateur.setMotDePasse(encoder.encode(utilisateur.getMotDePasse()));
        utilisateurRepository.save(utilisateur);
    }
}
