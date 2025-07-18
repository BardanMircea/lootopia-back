package com.sdv.lootopia.application.service;

import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.domain.ports.UtilisateurRepository;
import com.sdv.lootopia.web.dto.UtilisateurResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurResponseDTO getByEmail(String email) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByEmail(email);
        return UtilisateurResponseDTO.fromEntity(utilisateur.get());
    }

    public Utilisateur enregistrer(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }
}