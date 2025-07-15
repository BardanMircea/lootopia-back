package com.sdv.lootopia.application.service;

import com.sdv.lootopia.domain.model.*;
import com.sdv.lootopia.domain.ports.ChasseRepository;
import com.sdv.lootopia.domain.ports.CreusageRepository;
import com.sdv.lootopia.domain.ports.ParticipationRepository;
import com.sdv.lootopia.domain.ports.TransactionCouronnesRepository;
import com.sdv.lootopia.domain.util.DistanceUtil;
import com.sdv.lootopia.web.dto.CreusageRequestDTO;
import com.sdv.lootopia.web.dto.CreusageResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreusageService {
    private final CreusageRepository creusageRepository;
    private final ChasseRepository chasseRepository;
    private final ParticipationRepository participationRepository;
    private final TransactionCouronnesRepository transactionCouronnesRepository;

    private static final double DISTANCE_MAX_METRES = 20;

    @Transactional
    public CreusageResponseDTO tenterCreusage(CreusageRequestDTO dto, Utilisateur joueur) {
        Chasse chasse = chasseRepository.findById(dto.getChasseId())
                .orElseThrow(() -> new IllegalArgumentException("Chasse introuvable"));

        Participation participation = participationRepository.findByChasseAndUtilisateur(chasse, joueur)
                .orElseThrow(() -> new IllegalStateException("Non inscrit à cette chasse"));

        if (participation.getStatut() == Participation.Statut.TERMINE) {
            return new CreusageResponseDTO(false, "Vous avez déjà terminé cette chasse.", 0.0);
        }

        double distance = DistanceUtil.calculateDistance(
                dto.getLatitude(), dto.getLongitude(),
                chasse.getCache().getLatitude(), chasse.getCache().getLongitude()
        );

        if (distance <= DISTANCE_MAX_METRES) {
            TransactionCouronnes tx = new TransactionCouronnes();
            tx.setUtilisateur(joueur);
            tx.setMontant(chasse.getCache().getMontantRecompense());
            tx.setTypeOperation(TransactionCouronnes.TypeOperation.CREDIT);
            tx.setCommentaire("Récompense trouvée dans la chasse : " + chasse.getTitre());
            tx.setDateMouvement(LocalDateTime.now());
            transactionCouronnesRepository.save(tx);

            // Statut participation
            participation.setStatut(Participation.Statut.TERMINE);
            participationRepository.save(participation);

            return new CreusageResponseDTO(true, chasse.getCache().getMessageCacheTrouve(), chasse.getCache().getMontantRecompense());
        }

        return new CreusageResponseDTO(false, "Dommage, rien ici. Essayez encore !", 0.0);
    }

    public List<Creusage> getAll() { return creusageRepository.findAll(); }
    public Optional<Creusage> getById(Long id) { return creusageRepository.findById(id); }
    public Creusage save(Creusage creusage) { return creusageRepository.save(creusage); }
}