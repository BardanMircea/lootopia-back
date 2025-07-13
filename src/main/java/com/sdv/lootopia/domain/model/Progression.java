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
public class Progression {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "participation_id")
    private Participation participation;

    @ManyToOne
    @JoinColumn(name = "etape_id")
    private Etape etape;

    private LocalDateTime dateValidation;

    @Enumerated(EnumType.STRING)
    private Statut statut = Statut.EN_COURS;

    public enum Statut {
        EN_COURS, VALIDE
    }
}
