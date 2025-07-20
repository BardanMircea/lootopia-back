package com.sdv.lootopia.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    private Boolean cacheTrouvee = Boolean.FALSE;

    private LocalDateTime dateDecouverte;

    private String empreinteHash;
    
    @ManyToOne
    @JoinColumn(name = "joueur_id")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "chasse_id")
    private Chasse chasse;

    @OneToMany(mappedBy = "participation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Progression> progressions;

    private LocalDateTime dateInscription = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Statut statut = Statut.ACTIF;

    public enum Statut {
        EN_ATTENTE, ACTIF, ANNULE, TERMINE
    }
}
