package com.sdv.lootopia.domain.model;

import lombok.*;
import jakarta.persistence.*;

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

    private boolean mfaActive;

    @Column(nullable = false)
    private Double soldeCouronnes = 0.0;
}
