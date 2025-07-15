package com.sdv.lootopia.application.service;

import com.sdv.lootopia.domain.model.Chasse;
import com.sdv.lootopia.domain.model.Cache;
import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.domain.ports.ChasseRepository;
import com.sdv.lootopia.domain.ports.CacheRepository;
import com.sdv.lootopia.web.dto.ChasseResponseDTO;
import com.sdv.lootopia.web.dto.ChasseRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChasseService {
    private final ChasseRepository chasseRepository;
    private final CacheRepository cacheRepository;


    @Transactional
    public ChasseResponseDTO createChasse(ChasseRequestDTO dto, Utilisateur organisateur) {
        Chasse.TypeMonde typeMonde = Chasse.TypeMonde.valueOf(dto.getTypeMonde().toUpperCase());
        Cache.TypeRecompense typeRecompense = Cache.TypeRecompense.valueOf(dto.getTypeRecompense().toUpperCase());
        Chasse.Visibilite visibilite = Chasse.Visibilite.valueOf(dto.getVisibilite().toUpperCase());

        Chasse chasse = new Chasse();
        chasse.setTitre(dto.getTitre());
        chasse.setDescription(dto.getDescription());
        chasse.setTypeMonde(typeMonde);
        chasse.setOrganisateur(organisateur);
        chasse.setFraisParticipation(dto.getFraisParticipation());
        chasse.setVisibilite(visibilite);

        Chasse chasseSauvegardee = chasseRepository.save(chasse);

        Cache cache = new Cache();
        cache.setChasse(chasse);
        cache.setLatitude(dto.getLatitudeCache());
        cache.setLongitude(dto.getLongitudeCache());
        cache.setMessageCacheTrouve(dto.getMessageCacheTrouve());
        cache.setTypeRecompense(typeRecompense);
        cache.setMontantRecompense(dto.getMontantRecompense());
        cache.setDescription(dto.getDescription());

        cacheRepository.save(cache);

        return ChasseResponseDTO.fromEntity(chasseSauvegardee);
    }

    public List<ChasseResponseDTO> getChassesCreatedByUtilisateur(Long utilisateurId) {
        return chasseRepository.findByOrganisateurId(utilisateurId)
                .stream()
                .map(ChasseResponseDTO::fromEntity)
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
