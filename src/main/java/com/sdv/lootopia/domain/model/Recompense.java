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
    private Double valeurCouronnes;

    @Enumerated(EnumType.STRING)
    private TypeRecompense typeRecompense = TypeRecompense.COURONNES;

    public enum TypeRecompense {
        COURONNES, ARTEFACT
    }
}
