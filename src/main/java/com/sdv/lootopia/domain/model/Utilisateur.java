package com.sdv.lootopia.domain.model;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String motDePasse;
    private String pseudo;
    private LocalDateTime dateCreation;

    private String activationToken;
    private LocalDateTime activationTokenExpiration;
    private boolean compteActif = Boolean.FALSE;
    private boolean rgpdConsent;

    @OneToMany(mappedBy = "utilisateur",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participation> participations;

    @OneToMany(mappedBy = "organisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chasse> chasses;

    private boolean isPartenaire = Boolean.FALSE;
    private boolean mfaActive = Boolean.FALSE;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransactionCouronnes> transactions;


    @Column(nullable = false)
    private Double soldeCouronnes = 0.0;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    public enum Role {
        USER, ADMIN
    }
}
