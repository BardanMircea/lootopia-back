package com.sdv.lootopia.web.dto;

import com.sdv.lootopia.domain.model.Utilisateur;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UtilisateurResponseDTO {

    private Long id;
    private Double soldeCouronnes;
    private String pseudo;
    private Boolean isMfa;
    private Boolean isPartenaire;
    private LocalDateTime dateInscription;
    private String email;

    public static UtilisateurResponseDTO fromEntity(Utilisateur utilisateur) {
        UtilisateurResponseDTO dto = new UtilisateurResponseDTO();

        dto.setId(utilisateur.getId());
        dto.setSoldeCouronnes(utilisateur.getSoldeCouronnes());
        dto.setPseudo(utilisateur.getPseudo());
        dto.setEmail(utilisateur.getEmail());
        dto.setIsMfa(utilisateur.isMfaActive());
        dto.setIsPartenaire(utilisateur.isPartenaire());
        dto.setDateInscription(utilisateur.getDateCreation());

        return dto;
    }
}