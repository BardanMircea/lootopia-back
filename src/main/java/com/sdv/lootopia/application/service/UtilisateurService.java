package com.sdv.lootopia.application.service;

import com.sdv.lootopia.domain.model.Chasse;
import com.sdv.lootopia.domain.model.Participation;
import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.domain.ports.*;
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

    private final ChasseRepository chasseRepository;;
    private final UtilisateurRepository utilisateurRepository;
    private final ParticipationRepository participationRepository;
    private final EtapeRepository etapeRepository;
    private final CacheRepository cacheRepository;;
    private final ProgressionRepository progressionRepository;
    private final TransactionCouronnesRepository transactionCouronnesRepository;

    public UtilisateurResponseDTO getByEmail(String email) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByEmail(email);
        return UtilisateurResponseDTO.fromEntity(utilisateur.get());
    }

    @Transactional
    public Integer deleteUserById(Long userId) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Supprimer les progressions liées à chaque participation
        if (utilisateur.getParticipations() != null) {
            for (Participation participation : utilisateur.getParticipations()) {
                if (participation.getProgressions() != null) {
                    participation.getProgressions().forEach(p -> p.setParticipation(null));
                    progressionRepository.deleteAll(participation.getProgressions());
                    participation.getProgressions().clear();
                }
                participation.setUtilisateur(null);
                participationRepository.save(participation);
            }
            utilisateur.getParticipations().clear();
        }

        // Gérer les chasses de l'utilisateur
        if (utilisateur.getChasses() != null) {
            for (Chasse chasse : utilisateur.getChasses()) {
                // Étapes
                if (chasse.getEtapes() != null) {
                    chasse.getEtapes().forEach(e -> e.setChasse(null));
                    etapeRepository.saveAll(chasse.getEtapes());
                    chasse.getEtapes().clear();
                }

                // Cache
                if (chasse.getCache() != null) {
                    chasse.getCache().setChasse(null);
                    cacheRepository.save(chasse.getCache());
                    chasse.setCache(null);
                }

                chasse.setOrganisateur(null);
                chasseRepository.save(chasse);
            }
            utilisateur.getChasses().clear();
        }
        if (utilisateur.getTransactions() != null) {
            transactionCouronnesRepository.deleteAll(utilisateur.getTransactions());
            utilisateur.getTransactions().clear();
        }

        utilisateurRepository.save(utilisateur);
        utilisateurRepository.delete(utilisateur);

        return 1;
    }


    public List<UtilisateurResponseDTO> getAll() {

        List<UtilisateurResponseDTO> utilisateurResponseDTOList = new ArrayList<>();

        List<Utilisateur> users = this.utilisateurRepository.getAll().stream().filter(utilisateur -> utilisateur.getRole().equals(Utilisateur.Role.USER)).toList();
        for(Utilisateur u : users )
            utilisateurResponseDTOList.add(UtilisateurResponseDTO.fromEntity(u));

        return utilisateurResponseDTOList;
    }
}