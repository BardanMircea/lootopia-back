package com.sdv.lootopia.domain.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recompense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Double valeurCouronnes = 0.0;

    @ManyToOne
    @JoinColumn(name = "chasse_id")
    private Chasse chasse;

    @Enumerated(EnumType.STRING)
    private TypeRecompense typeRecompense = TypeRecompense.COURONNES;

    public enum TypeRecompense {
        COURONNES, ARTEFACT
    }
}
