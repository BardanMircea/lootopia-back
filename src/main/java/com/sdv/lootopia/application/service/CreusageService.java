package com.sdv.lootopia.application.service;

import com.sdv.lootopia.domain.model.*;
import com.sdv.lootopia.domain.ports.*;
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

        // met à jour la solde du joueur si le creusage a couté des couronnes
        Double nouvelleSoldeJoueur = joueur.getSoldeCouronnes() - dto.getCoutEnCouronnes();
        joueur.setSoldeCouronnes(nouvelleSoldeJoueur);


        // et log la transaction
        TransactionCouronnes tx = new TransactionCouronnes();
        tx.setUtilisateur(joueur);
        tx.setMontant(dto.getCoutEnCouronnes());
        tx.setTypeOperation(TransactionCouronnes.TypeOperation.DEBIT);
        tx.setCommentaire("Montant payé pour creuser dans " + chasse.getTitre());
        tx.setDateMouvement(LocalDateTime.now());
        transactionCouronnesRepository.save(tx);

        Creusage creusage = new Creusage();
        creusage.setDate(LocalDateTime.now());
        creusage.setParticipation(participation);
        creusage.setCoutEnCouronnes(dto.getCoutEnCouronnes());

        if (distance <= DISTANCE_MAX_METRES) {
            TransactionCouronnes tx1 = new TransactionCouronnes();
            tx1.setUtilisateur(joueur);
            tx1.setMontant(chasse.getCache().getMontantRecompense());
            tx1.setTypeOperation(TransactionCouronnes.TypeOperation.CREDIT);
            tx1.setCommentaire("Récompense trouvée dans la chasse : " + chasse.getTitre());
            tx1.setDateMouvement(LocalDateTime.now());
            transactionCouronnesRepository.save(tx1);

            participation.setStatut(Participation.Statut.TERMINE);
            participationRepository.save(participation);

            creusage.setReussi(true);
            creusageRepository.save(creusage);

            // met à jour la solde du joueur si le creusage est réussi et qu'il a gaigné
            nouvelleSoldeJoueur += chasse.getCache().getMontantRecompense();
            joueur.setSoldeCouronnes(nouvelleSoldeJoueur);
            utilisateurRepository.save(joueur);

            return new CreusageResponseDTO(true, chasse.getCache().getMessageCacheTrouve(), chasse.getCache().getMontantRecompense(), nouvelleSoldeJoueur);
        }
        creusage.setReussi(false);
        creusage.setDistanceErreurM(distance);
        creusageRepository.save(creusage);
        utilisateurRepository.save(joueur);
        return new CreusageResponseDTO(false, "Dommage, rien ici. Essayez encore !", 0.0, nouvelleSoldeJoueur);
    }

    public List<Creusage> getAll() { return creusageRepository.findAll(); }
    public Optional<Creusage> getById(Long id) { return creusageRepository.findById(id); }
    public Creusage save(Creusage creusage) { return creusageRepository.save(creusage); }
}