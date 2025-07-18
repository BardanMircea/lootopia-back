package com.sdv.lootopia.web.controller;

import com.sdv.lootopia.domain.model.TransactionCouronnes;
import com.sdv.lootopia.application.service.TransactionService;
import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.web.dto.TransactionRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/debloquer-creusage")
    public ResponseEntity<?> createDebitTx(@RequestBody TransactionRequestDTO transactionRequestDTO,
                                           @AuthenticationPrincipal(expression = "utilisateur") Utilisateur utilisateur) {
        this.transactionService.createDebitTx(transactionRequestDTO.getCout(), utilisateur);
        return ResponseEntity.ok(Map.of("message", "Solde débité !", "nouveauSolde", utilisateur.getSoldeCouronnes()));
    }

    @GetMapping
    public ResponseEntity<List<TransactionCouronnes>> getAll() {
        return ResponseEntity.ok(transactionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionCouronnes> getById(@PathVariable Long id) {
        Optional<TransactionCouronnes> t = transactionService.getById(id);
        return t.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TransactionCouronnes> create(@RequestBody TransactionCouronnes t) {
        return ResponseEntity.ok(transactionService.save(t));
    }
}