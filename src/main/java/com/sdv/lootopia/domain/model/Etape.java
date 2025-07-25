package com.sdv.lootopia.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etape {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ordre;
    private String consigne;
    private Double latitudeCache;
    private Double longitudeCache;
    private String passphrase;
    private double prixValidationDirecte;

    @ManyToOne
    @JoinColumn(name = "chasse_id")
    private Chasse chasse;

}