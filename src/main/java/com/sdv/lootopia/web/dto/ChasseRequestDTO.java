package com.sdv.lootopia.web.dto;

import com.sdv.lootopia.domain.model.Cache;
import com.sdv.lootopia.domain.model.Chasse;
import com.sdv.lootopia.domain.model.Etape;
import com.sdv.lootopia.domain.ports.ChasseRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@Getter
@Setter
public class ChasseRequestDTO {

    @NotBlank
    private Long id;

    @NotBlank
    private String titre;

    @NotBlank
    private String description;

    @NotNull
    private Double latitudeCache;

    @NotNull
    private Double longitudeCache;

    private LocalDateTime  dateDebut;
    private LocalDateTime  dateFin;
    private LocalDateTime  dateCreation;
    private String nombreParticipants;

    @NotBlank
    private String monde; // "CARTOGRAPHIQUE" ou "REEL"

    private String messageCacheTrouve;

    private Double fraisParticipation;

    private String typeRecompense = "COURONNES"; //  pour MVP uniquement COURONNES

    private Double montantRecompense = 100.0; // MVP

    private String visibilite; // PUBLIC ou PRIVE (PUBLIC par default pour le MVP)


    public static Chasse fromDto(ChasseRequestDTO dto, ChasseRepository chasseRepository) {
        Chasse chasse = chasseRepository.findById(dto.getId()).orElse(null);
        assert chasse != null;

        chasse.setTitre(dto.getTitre());
        chasse.setDescription(dto.getDescription());
        chasse.getCache().setLatitude(dto.getLatitudeCache());
        chasse.getCache().setLongitude(dto.getLongitudeCache());
        chasse.getCache().setMontantRecompense(dto.getMontantRecompense());
        chasse.getCache().setMessageCacheTrouve(dto.getMessageCacheTrouve());
        chasse.getCache().setTypeRecompense(Cache.TypeRecompense.valueOf(dto.getTypeRecompense()));
        if( dto.getNombreParticipants() != null && Integer.parseInt(dto.getNombreParticipants()) != 0)
            chasse.setMaxParticipants(dto.getNombreParticipants());
        chasse.setFraisParticipation(dto.getFraisParticipation());
        chasse.setTypeMonde(Chasse.TypeMonde.valueOf(dto.getMonde()));
        chasse.setVisibilite(Chasse.Visibilite.valueOf(dto.getVisibilite()));
        chasse.setDateDebut(dto.getDateDebut());
        chasse.setDateFin(dto.getDateFin());
        return chasse;
    }
}
