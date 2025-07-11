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
public class Chasse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String description;
    private Double latitudeCache;
    private Double longitudeCache;

    @Enumerated(EnumType.STRING)
    private TypeMonde typeMonde;

    private Double fraisParticipation;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    public enum TypeMonde {
        Reel, Cartographique
    }
}

