package com.sdv.lootopia.application.service;

import com.sdv.lootopia.domain.model.Chasse;
import com.sdv.lootopia.domain.model.Cache;
import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.domain.ports.ChasseRepository;
import com.sdv.lootopia.domain.ports.CacheRepository;
import com.sdv.lootopia.web.dto.ChasseResponseDTO;
import com.sdv.lootopia.web.dto.ChasseRequestDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChasseService {
    private final ChasseRepository chasseRepository;
    private final CacheRepository cacheRepository;


    @Transactional
    public ChasseResponseDTO createChasse(ChasseRequestDTO dto, Utilisateur organisateur) {
        Chasse.TypeMonde typeMonde = Chasse.TypeMonde.valueOf(dto.getMonde().toUpperCase());
        Cache.TypeRecompense typeRecompense = Cache.TypeRecompense.valueOf(dto.getTypeRecompense().toUpperCase());
        Chasse.Visibilite visibilite = Chasse.Visibilite.valueOf(dto.getVisibilite().toUpperCase());

        Chasse chasse = new Chasse();
        chasse.setTitre(dto.getTitre());
        chasse.setDescription(dto.getDescription());
        chasse.setTypeMonde(typeMonde);
        chasse.setOrganisateur(organisateur);
        chasse.setFraisParticipation(dto.getFraisParticipation());
        chasse.setVisibilite(visibilite);
        chasse.setDateDebut(dto.getDateDebut());
        chasse.setDateFin(dto.getDateFin());
        chasse.setDateCreation(LocalDateTime.now());
        if (Objects.equals(dto.getNombreParticipants(), "0")) {
            chasse.setMaxParticipants("Illimité");
        } else {
            chasse.setMaxParticipants(dto.getNombreParticipants());
        }
        chasse.setStatut(Chasse.Statut.Active);

        Cache cache = new Cache();
        cache.setLatitude(dto.getLatitudeCache());
        cache.setLongitude(dto.getLongitudeCache());
        cache.setMessageCacheTrouve(dto.getMessageCacheTrouve());
        cache.setTypeRecompense(typeRecompense);
        cache.setMontantRecompense(dto.getMontantRecompense());

        cache.setChasse(chasseRepository.save(chasse));
        chasse.setCache(cacheRepository.save(cache));

        return ChasseResponseDTO.fromEntity(chasseRepository.save(chasse));
    }

    public List<ChasseResponseDTO> getChassesCreatedByUtilisateur(Long utilisateurId) {
        return chasseRepository.findByOrganisateurId(utilisateurId)
                .stream()
                .map(ChasseResponseDTO::fromEntity)
                .toList();
    }

    public Page<Chasse> getChassesPubliquesEtActives(Pageable pageable) {
        return chasseRepository.findByVisibiliteAndStatut(Chasse.Visibilite.PUBLIC, Chasse.Statut.Active, pageable);
    }

    public List<Chasse> getAll() {
        return chasseRepository.findAll();
    }

    public ChasseResponseDTO getById(Long id) {
        Optional<Chasse> chasse = this.chasseRepository.findById(id);
        return ChasseResponseDTO.fromEntity(chasse.get());
    }

    public Chasse save(Chasse chasse) {
        return chasseRepository.save(chasse);
    }

    public ChasseResponseDTO updateChasse(Long chasseId, @Valid ChasseRequestDTO chasseRequestDTO) {
        Chasse chasseFromRepo = this.chasseRepository.findById(chasseId).get();
        chasseFromRepo.setId(chasseId);
        chasseFromRepo = ChasseRequestDTO.fromDto(chasseRequestDTO, chasseRepository);
        return ChasseResponseDTO.fromEntity(chasseRepository.save(chasseFromRepo));

    }
}
