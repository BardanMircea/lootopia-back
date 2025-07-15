package com.sdv.lootopia.application.service;

import com.sdv.lootopia.domain.model.Chasse;
import com.sdv.lootopia.domain.model.Participation;
import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.domain.ports.ChasseRepository;
import com.sdv.lootopia.domain.ports.ParticipationRepository;
import com.sdv.lootopia.web.dto.ParticipationRequestDTO;
import com.sdv.lootopia.web.dto.ParticipationResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParticipationService {
    private final ParticipationRepository participationRepository;
    private final ChasseRepository chasseRepository;


    @Transactional
    public boolean cancelParticipation(Long participationId, Long utilisateurId) {
        Optional<Participation> participationOpt = participationRepository.findById(participationId);

        if (participationOpt.isEmpty()) return false;

        Participation participation = participationOpt.get();

        if (!participation.getUtilisateur().getId().equals(utilisateurId)) return false;

        if (participation.getStatut() != Participation.Statut.ACTIF &&
                participation.getStatut() != Participation.Statut.EN_ATTENTE)
            return false;

        participation.setStatut(Participation.Statut.ANNULE);
        participationRepository.save(participation);

        return true;
    }

    @Transactional
    public ParticipationResponseDTO participateToChasse(Utilisateur utilisateur, ParticipationRequestDTO dto) {
        // Vérifier si la chasse existe
        Chasse chasse = chasseRepository.findById(dto.getChasseId())
                .orElseThrow(() -> new IllegalArgumentException("Chasse introuvable"));

        // Vérifier si déjà inscrit
        participationRepository.findByUtilisateurIdAndChasseId(utilisateur.getId(), chasse.getId())
                .ifPresent(p -> {
                    throw new IllegalStateException("Utilisateur déjà inscrit à cette chasse");
                });

        // Verifier si organisateur
        if(Objects.equals(chasse.getOrganisateur().getId(), utilisateur.getId()))
                    throw new IllegalStateException("Utilisateur ne peut pas participer, car l'organisateur de cette chasse");

        // à vérifier si la chasse est toujours ouverte (dateFin > now ?)

        Participation participation = new Participation();
        participation.setUtilisateur(utilisateur);
        participation.setChasse(chasse);
        participation.setDateInscription(LocalDateTime.now());
        if (chasse.getEtapes() != null && !chasse.getEtapes().isEmpty()) {
            participation.setEtapeCourante(1);
            participation.setEligibleCreusage(false);
        }
        else {
            participation.setEtapeCourante(-1);
            participation.setEligibleCreusage(true);
        }

        return ParticipationResponseDTO.fromEntity(participationRepository.save(participation));
    }

    public List<ParticipationResponseDTO> getActiveParticipationsForUtilisateur(Long utilisateurId) {
        return participationRepository.findByUtilisateurIdAndStatut(utilisateurId, Participation.Statut.ACTIF)
                .stream()
                .map(ParticipationResponseDTO::fromEntity)
                .toList();
    }


    public List<Participation> getAll() { return participationRepository.findAll(); }
    public Optional<Participation> getById(Long id) { return participationRepository.findById(id); }
    public Participation save(Participation p) { return participationRepository.save(p); }
}