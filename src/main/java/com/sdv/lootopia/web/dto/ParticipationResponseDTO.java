package com.sdv.lootopia.web.dto;

import com.sdv.lootopia.domain.model.Participation;
import com.sdv.lootopia.domain.model.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipationResponseDTO {
    private Long id;

    private Long chasseId;

    private List<String> participants;

    private LocalDateTime inscritDepuis;

    private Integer etapeCourante;

    private Boolean eligibleCreusage;


    public static ParticipationResponseDTO fromEntity( Participation participation) {
        ParticipationResponseDTO dto = new ParticipationResponseDTO();
        dto.setId(participation.getId());
        dto.setChasseId(participation.getChasse().getId());
        if (participation.getChasse().getEtapes() != null && !participation.getChasse().getEtapes().isEmpty()) {
            dto.setEtapeCourante(1);
            dto.setEligibleCreusage(false);
        }
         else {
            dto.setEtapeCourante(-1);
            dto.setEligibleCreusage(true);
        }

        dto.setInscritDepuis(participation.getDateInscription());

        dto.setParticipants(new ArrayList<>());
        for (Participation p : participation.getChasse().getParticipations())
            dto.getParticipants().add(p.getUtilisateur().getPseudo());

        return dto;
    }
}