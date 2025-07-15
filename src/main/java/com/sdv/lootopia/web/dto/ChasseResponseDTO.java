package com.sdv.lootopia.web.dto;

import com.sdv.lootopia.domain.model.Chasse;
import com.sdv.lootopia.domain.model.Etape;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChasseResponseDTO {

    private Long id;
    private String titre;
    private String description;
    private String monde;          // CARTOGRAPHIQUE ou REEL
    private String visibilite;     // PUBLIC ou PRIVE
    private String createur;       // Pseudo de l’organisateur
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Integer nombreEtapes;
    private Integer nombreParticipants;
    private Double montantRecompense;
    private List<EtapeReponseDTO> etapes;

    // Méthode pour mapper depuis une entité Chasse :
    public static ChasseResponseDTO fromEntity(Chasse chasse) {
        ChasseResponseDTO dto = new ChasseResponseDTO();
        dto.setId(chasse.getId());
        dto.setTitre(chasse.getTitre());
        dto.setDescription(chasse.getDescription());
        dto.setMonde(chasse.getTypeMonde().name());
        dto.setVisibilite(chasse.getVisibilite().name());
        dto.setCreateur(chasse.getOrganisateur().getPseudo());
        dto.setDateDebut(chasse.getDateDebut());
        dto.setDateFin(chasse.getDateFin());
        dto.setNombreEtapes(chasse.getEtapes() != null ? chasse.getEtapes().size() : 0);
        dto.setNombreParticipants(chasse.getParticipations() != null ? chasse.getParticipations().size() : 0);
        dto.setEtapes(new ArrayList<>());
        dto.setMontantRecompense(chasse.getCache().getMontantRecompense());

        if(chasse.getEtapes() != null)
            for (Etape e : chasse.getEtapes()) dto.getEtapes().add(EtapeReponseDTO.fromEntity(e));

        return dto;
    }

}
