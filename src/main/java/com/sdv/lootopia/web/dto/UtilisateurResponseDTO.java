package com.sdv.lootopia.web.dto;

import com.sdv.lootopia.domain.model.Utilisateur;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtilisateurResponseDTO {

    private Double soldeCouronnes;

    public static UtilisateurResponseDTO fromEntity(Utilisateur utilisateur) {
        UtilisateurResponseDTO dto = new UtilisateurResponseDTO();

        dto.setSoldeCouronnes(utilisateur.getSoldeCouronnes());

        return dto;
    }
}