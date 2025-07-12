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

    @OneToMany(mappedBy = "utilisateur")
    private List<Participation> participations;

    private boolean isPartenaire = Boolean.FALSE;
    private boolean mfaActive = Boolean.FALSE;

    @Column(nullable = false)
    private Double soldeCouronnes = 0.0;

    private Role role = Role.JOUEUR;

    public enum Role {
        JOUEUR, ORGANISATEUR
    }
}
