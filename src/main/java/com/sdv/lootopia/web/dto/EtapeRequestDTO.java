package com.sdv.lootopia.web.dto;

import com.sdv.lootopia.domain.model.RepereRa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EtapeRequestDTO {

    @NotNull
    private Long chasseId;

    @NotNull
    private Integer ordre;

    @NotBlank
    private String consigne;

    private String passphrase; // uniquement si validation se fait par PASSPHRASE
    private Double prix_validation_directe;

    private Double latitudeCache;   // uniquement si si validation se fait par CACHE
    private Double longitudeCache;  // uniquement si validation se fait par CACHE
    private RepereRa repereRa; // uniquement si validation se fait par Repere
}
