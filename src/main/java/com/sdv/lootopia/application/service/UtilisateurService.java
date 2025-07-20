package com.sdv.lootopia.application.service;

import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.domain.ports.UtilisateurRepository;
import com.sdv.lootopia.web.dto.UtilisateurResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurResponseDTO getByEmail(String email) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByEmail(email);
        return UtilisateurResponseDTO.fromEntity(utilisateur.get());
    }

    @Transactional
    public Integer deleteUserById(Long userId) {
        return this.utilisateurRepository.deleteById(userId);
    }

    public List<UtilisateurResponseDTO> getAll() {

        List<UtilisateurResponseDTO> utilisateurResponseDTOList = new ArrayList<>();

        List<Utilisateur> users = this.utilisateurRepository.getAll().stream().filter(utilisateur -> utilisateur.getRole().equals(Utilisateur.Role.USER)).toList();
        for(Utilisateur u : users )
            utilisateurResponseDTOList.add(UtilisateurResponseDTO.fromEntity(u));

        return utilisateurResponseDTOList;
    }
}