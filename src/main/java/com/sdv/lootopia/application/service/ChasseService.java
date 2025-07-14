package com.sdv.lootopia.application.service;

import com.sdv.lootopia.domain.model.Chasse;
import com.sdv.lootopia.domain.model.Recompense;
import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.domain.ports.ChasseRepository;
import com.sdv.lootopia.domain.ports.RecompenseRepository;
import com.sdv.lootopia.web.dto.ChasseApercuDTO;
import com.sdv.lootopia.web.dto.NouvelleChasseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChasseService {
    private final ChasseRepository chasseRepository;
    private final RecompenseRepository recompenseRepository;


    @Transactional
    public ChasseApercuDTO createChasse(NouvelleChasseDTO dto, Utilisateur organisateur) {
        Chasse.TypeMonde typeMonde = Chasse.TypeMonde.valueOf(dto.getTypeMonde().toUpperCase());
        Recompense.TypeRecompense typeRecompense = Recompense.TypeRecompense.valueOf(dto.getTypeRecompense().toUpperCase());
        Chasse.Visibilite visibilite = Chasse.Visibilite.valueOf(dto.getVisibilite().toUpperCase());

        Chasse chasse = new Chasse();
        chasse.setTitre(dto.getTitre());
        chasse.setDescription(dto.getDescription());
        chasse.setLatitudeCache(dto.getLatitudeCache());
        chasse.setLongitudeCache(dto.getLongitudeCache());
        chasse.setTypeMonde(typeMonde);
        chasse.setOrganisateur(organisateur);
        chasse.setFraisParticipation(dto.getFraisParticipation());
        chasse.setVisibilite(visibilite);

        Chasse chasseSauvegardee = chasseRepository.save(chasse);

        Recompense recompense = new Recompense();
        recompense.setChasse(chasse);
        recompense.setTypeRecompense(typeRecompense);
        recompense.setValeurCouronnes(dto.getMontantRecompense());
        recompense.setDescription("Récompense pour avoir trouvé la cache");

        recompenseRepository.save(recompense);

        return ChasseApercuDTO.fromEntity(chasseSauvegardee);
    }

    public List<ChasseApercuDTO> getChassesCreatedByUtilisateur(Long utilisateurId) {
        return chasseRepository.findByOrganisateurId(utilisateurId)
                .stream()
                .map(ChasseApercuDTO::fromEntity)
                .toList();
    }

    public List<Chasse> getAll() {
        return chasseRepository.findAll();
    }

    public Optional<Chasse> getById(Long id) {
        return chasseRepository.findById(id);
    }

    public Chasse save(Chasse chasse) {
        return chasseRepository.save(chasse);
    }
}
