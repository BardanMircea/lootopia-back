package com.sdv.lootopia.domain.model;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionCouronnes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Utilisateur utilisateur;

    private Double montant;

    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;

    private String commentaire;

    private LocalDateTime dateMouvement;

    public enum TypeOperation {
        CREDIT, DEBIT
    }
}

