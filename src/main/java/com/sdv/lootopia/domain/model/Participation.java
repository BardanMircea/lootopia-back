package com.sdv.lootopia.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean eligibleCreusage;

    private Integer etapeCourante;

    @ManyToOne
    @JoinColumn(name = "joueur_id")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "chasse_id")
    private Chasse chasse;

    private LocalDateTime dateInscription = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Statut statut = Statut.ACTIF;

    public enum Statut {
        EN_ATTENTE, ACTIF, ANNULE, TERMINE
    }
}
