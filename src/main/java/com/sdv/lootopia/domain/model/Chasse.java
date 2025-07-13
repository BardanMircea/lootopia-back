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
public class Chasse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String description;
    private Double latitudeCache;
    private Double longitudeCache;
    private Integer maxParticipants;

    @ManyToOne
    @JoinColumn(name = "organisateur_id")
    private Utilisateur organisateur;

    @Enumerated(EnumType.STRING)
    private TypeMonde typeMonde = TypeMonde.CARTOGRAPHIQUE;

    @OneToMany(mappedBy = "chasse")
    private List<Participation> participations;

    @OneToMany(mappedBy = "chasse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Etape> etapes;


    @Enumerated(EnumType.STRING)
    private Visibilite visibilite = Visibilite.PUBLIC;

    private Double fraisParticipation = 0.0;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    public enum TypeMonde {
        REEL, CARTOGRAPHIQUE
    }

    public enum Visibilite {
        PUBLIC, PRIVATE
    }
}

