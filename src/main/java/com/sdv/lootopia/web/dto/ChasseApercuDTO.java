package com.sdv.lootopia.web.dto;

import com.sdv.lootopia.domain.model.Chasse;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChasseApercuDTO {

    private String titre;
    private String description;
    private String monde;          // CARTOGRAPHIQUE ou REEL
    private String visibilite;     // PUBLIC ou PRIVE
    private String createur;       // Pseudo de l’organisateur
    private Double latitudeCache;
    private Double longitudeCache;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Integer nombreEtapes;
    private Integer nombreParticipants;

    // Méthode utilitaire pour mapper depuis une entité Chasse :
    public static ChasseApercuDTO fromEntity(Chasse chasse) {
        ChasseApercuDTO dto = new ChasseApercuDTO();
        dto.setTitre(chasse.getTitre());
        dto.setDescription(chasse.getDescription());
        dto.setMonde(chasse.getTypeMonde().name());
        dto.setVisibilite(chasse.getVisibilite().name());
        dto.setCreateur(chasse.getOrganisateur().getPseudo());
        dto.setLatitudeCache(chasse.getLatitudeCache());
        dto.setLongitudeCache(chasse.getLongitudeCache());
        dto.setDateDebut(chasse.getDateDebut());
        dto.setDateFin(chasse.getDateFin());
        dto.setNombreEtapes(chasse.getEtapes() != null ? chasse.getEtapes().size() : 0);
        dto.setNombreParticipants(chasse.getParticipations() != null ? chasse.getParticipations().size() : 0);
        return dto;
    }

    // Getters & setters...
}
