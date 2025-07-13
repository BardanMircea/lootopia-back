package com.sdv.lootopia.application.service;

import com.sdv.lootopia.domain.model.Chasse;
import com.sdv.lootopia.domain.model.Recompense;
import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.domain.ports.ChasseRepository;
import com.sdv.lootopia.infrastructure.repository.JpaRecompenseRepository;
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
    private final JpaRecompenseRepository recompenseRepo;


    @Transactional
    public Chasse creerNouvelleChasse(NouvelleChasseDTO dto, Utilisateur organisateur) {
        Chasse.TypeMonde typeMonde = Chasse.TypeMonde.valueOf(dto.getTypeMonde().toUpperCase());
        Recompense.TypeRecompense typeRecompense = Recompense.TypeRecompense.valueOf(dto.getTypeRecompense().toUpperCase());

        Chasse chasse = new Chasse();
        chasse.setTitre(dto.getTitre());
        chasse.setDescription(dto.getDescription());
        chasse.setLatitudeCache(dto.getLatitudeCache());
        chasse.setLongitudeCache(dto.getLongitudeCache());
        chasse.setTypeMonde(typeMonde);
        chasse.setOrganisateur(organisateur);
        chasse.setFraisParticipation(dto.getFraisParticipation());

        chasseRepository.save(chasse);

        Recompense recompense = new Recompense();
        recompense.setChasse(chasse);
        recompense.setTypeRecompense(typeRecompense);
        recompense.setValeurCouronnes(dto.getMontantRecompense());
        recompense.setDescription("Récompense pour avoir trouvé la cache");

        recompenseRepo.save(recompense);

        return chasse;
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
