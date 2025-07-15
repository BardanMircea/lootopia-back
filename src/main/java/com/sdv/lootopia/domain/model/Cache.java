package com.sdv.lootopia.domain.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;
    private Double longitude;
    private Double montantRecompense;
    private String messageCacheTrouve;

    @OneToOne
    @JoinColumn(name = "chasse_id")
    private Chasse chasse;

    @Enumerated(EnumType.STRING)
    private TypeRecompense typeRecompense = TypeRecompense.COURONNES;

    public enum TypeRecompense {
        COURONNES, ARTEFACT
    }
}
