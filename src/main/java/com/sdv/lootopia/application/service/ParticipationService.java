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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParticipationService {
    private final ParticipationRepository participationRepository;
    private final ChasseRepository chasseRepository;

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

        // à vérifier si la chasse est toujours ouverte (dateFin > now ?)

        Participation participation = new Participation();
        participation.setUtilisateur(utilisateur);
        participation.setChasse(chasse);
        participation.setDateInscription(LocalDateTime.now());

        return ParticipationResponseDTO.fromEntity(participationRepository.save(participation));
    }

    public List<Participation> getAll() { return participationRepository.findAll(); }
    public Optional<Participation> getById(Long id) { return participationRepository.findById(id); }
    public Participation save(Participation p) { return participationRepository.save(p); }
}