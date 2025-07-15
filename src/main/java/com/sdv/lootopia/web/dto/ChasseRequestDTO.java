package com.sdv.lootopia.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ChasseRequestDTO {

    @NotBlank
    private String titre;

    @NotBlank
    private String description;

    @NotNull
    private Double latitudeCache;

    @NotNull
    private Double longitudeCache;

    @NotBlank
    private String typeMonde; // "CARTOGRAPHIQUE" ou "REEL"

    private String messageCacheTrouve;

    private Double fraisParticipation = 0.0;

    private String typeRecompense = "COURONNES"; //  pour MVP uniquement COURONNES

    private Double montantRecompense = 100.0; // MVP

    private String visibilite; // PUBLIC ou PRIVE (PUBLIC par default pour le MVP)
}
