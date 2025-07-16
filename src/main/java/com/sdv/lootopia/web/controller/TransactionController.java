package com.sdv.lootopia.web.controller;

import com.sdv.lootopia.domain.model.TransactionCouronnes;
import com.sdv.lootopia.application.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
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