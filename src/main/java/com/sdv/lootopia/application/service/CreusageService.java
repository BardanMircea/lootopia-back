package com.sdv.lootopia.application.service;

import com.sdv.lootopia.domain.model.*;
import com.sdv.lootopia.domain.ports.*;
import com.sdv.lootopia.domain.util.DistanceUtil;
import com.sdv.lootopia.domain.util.HashingUtil;
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
    private final UtilisateurRepository utilisateurRepository;

    private static final double DISTANCE_MAX_METRES = 20;

    @Transactional
    public CreusageResponseDTO creuser(CreusageRequestDTO dto, Utilisateur joueur) {
        Chasse chasse = chasseRepository.findById(dto.getChasseId())
                .orElseThrow(() -> new IllegalArgumentException("Chasse introuvable"));

        Participation participation = participationRepository.findByChasseAndUtilisateur(chasse, joueur)
                .orElseThrow(() -> new IllegalStateException("Non inscrit à cette chasse"));

        if (participation.getStatut() == Participation.Statut.TERMINE) {
            throw new IllegalStateException("Vous avez déjà terminé cette chasse.");
        }

        if(!participation.getEligibleCreusage())
            throw new IllegalStateException("Vous n'étes pas eligible de creuser (vous avez encore des étapes à franchir).");

        double distance = DistanceUtil.calculateDistance(
                dto.getLatitude(), dto.getLongitude(),
                chasse.getCache().getLatitude(), chasse.getCache().getLongitude()
        );

        Creusage creusage = new Creusage();
        creusage.setDate(LocalDateTime.now());
        creusage.setParticipation(participation);
        creusage.setDistanceErreurM(distance);

        Double soldeJoueur = joueur.getSoldeCouronnes();
        if (distance <= DISTANCE_MAX_METRES) {
            TransactionCouronnes tx1 = new TransactionCouronnes();
            tx1.setUtilisateur(joueur);
            tx1.setMontant(chasse.getCache().getMontantRecompense());
            tx1.setTypeOperation(TransactionCouronnes.TypeOperation.CREDIT);
            tx1.setCommentaire("Récompense trouvée dans la chasse : " + chasse.getTitre());
            tx1.setDateMouvement(LocalDateTime.now());
            transactionCouronnesRepository.save(tx1);

            participation.setStatut(Participation.Statut.TERMINE);
            participation.setCacheTrouvee(true);
            participation.setDateDecouverte(LocalDateTime.now());
            participation.setEmpreinteHash(HashingUtil.sha256(
                    joueur.getId() + "-" +
                            chasse.getId() + "-" +
                            participation.getDateDecouverte()
            ));
            participationRepository.save(participation);

            creusage.setReussi(true);
            creusageRepository.save(creusage);

            // met à jour la solde du joueur si le creusage est réussi et qu'il a gaigné
            soldeJoueur += chasse.getCache().getMontantRecompense();
            joueur.setSoldeCouronnes(soldeJoueur);
            utilisateurRepository.save(joueur);

            return new CreusageResponseDTO(true, chasse.getCache().getMessageCacheTrouve(), chasse.getCache().getMontantRecompense(), soldeJoueur, creusage.getDistanceErreurM());
        }
        creusage.setReussi(false);
        creusage.setDistanceErreurM(distance);
        creusageRepository.save(creusage);
        utilisateurRepository.save(joueur);
        return new CreusageResponseDTO(false, "Dommage, rien ici. Essayez encore !", 0.0, creusage.getDistanceErreurM(), soldeJoueur);
    }

    public List<Creusage> getAll() { return creusageRepository.findAll(); }
    public Optional<Creusage> getById(Long id) { return creusageRepository.findById(id); }
    public Creusage save(Creusage creusage) { return creusageRepository.save(creusage); }
}