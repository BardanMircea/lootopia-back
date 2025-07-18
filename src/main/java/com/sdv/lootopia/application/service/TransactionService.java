package com.sdv.lootopia.application.service;

import com.sdv.lootopia.domain.model.TransactionCouronnes;
import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.domain.ports.TransactionCouronnesRepository;
import com.sdv.lootopia.domain.ports.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionCouronnesRepository transactionRepository;
    private final UtilisateurRepository utilisateurRepository;

    public List<TransactionCouronnes> getAll() { return transactionRepository.findAll(); }
    public Optional<TransactionCouronnes> getById(Long id) { return transactionRepository.findById(id); }
    public TransactionCouronnes save(TransactionCouronnes t) { return transactionRepository.save(t); }

    public void createDebitTx(Double coutTx, Utilisateur utilisateur) {
        TransactionCouronnes transactionCouronnes = new TransactionCouronnes();
        transactionCouronnes.setDateMouvement(LocalDateTime.now());
        transactionCouronnes.setCommentaire("Somme payée pour débloquer un creusage : " + coutTx.toString()+ " couronnes");
        transactionCouronnes.setTypeOperation(TransactionCouronnes.TypeOperation.DEBIT);
        transactionCouronnes.setMontant(coutTx);
        transactionCouronnes.setUtilisateur(utilisateur);
        utilisateur.setSoldeCouronnes(utilisateur.getSoldeCouronnes() - coutTx);
        transactionRepository.save(transactionCouronnes);
        utilisateurRepository.save(utilisateur);
    }
}
