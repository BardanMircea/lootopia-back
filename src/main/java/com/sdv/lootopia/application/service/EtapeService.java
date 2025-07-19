package com.sdv.lootopia.application.service;

import com.sdv.lootopia.domain.model.Chasse;
import com.sdv.lootopia.domain.model.Etape;
import com.sdv.lootopia.domain.model.Utilisateur;
import com.sdv.lootopia.domain.ports.ChasseRepository;
import com.sdv.lootopia.domain.ports.EtapeRepository;
import com.sdv.lootopia.web.dto.EtapeRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EtapeService {
    private final EtapeRepository etapeRepository;
    private final ChasseRepository chasseRepository;

    public List<Etape> getAll() { return etapeRepository.findAll(); }
    public Optional<Etape> getById(Long id) { return etapeRepository.findById(id); }
    public Etape save(Etape etape) { return etapeRepository.save(etape); }

    public Chasse ajouterEtape(EtapeRequestDTO dto, Utilisateur organisateur) {
        Chasse chasse = chasseRepository.findById(dto.getChasseId())
                .orElseThrow(() -> new IllegalArgumentException("Chasse introuvable"));

        if (!chasse.getOrganisateur().getId().equals(organisateur.getId())) {
            throw new SecurityException("Vous n'êtes pas l'organisateur de cette chasse");
        }

        if (chasse.getTypeMonde() != Chasse.TypeMonde.CARTOGRAPHIQUE) {
            throw new IllegalArgumentException("Étapes uniquement disponibles pour les chasses cartographiques dans le MVP");
        }

        Etape etape = new Etape();
        etape.setChasse(chasse);
        etape.setConsigne(dto.getConsigne());
        etape.setOrdre(dto.getOrdre());
        etape.setPassphrase(dto.getPassphrase());
        etape.setLatitudeCache(dto.getLatitudeCache());
        etape.setLongitudeCache(dto.getLongitudeCache());
        etape.setPrixValidationDirecte(dto.getPrixValidationDirecte());

        etapeRepository.save(etape);

        return chasseRepository.findById(chasse.getId()).get();
    }

    public List<Etape> listerEtapesParChasse(Long chasseId) {
        return etapeRepository.findByChasseId(chasseId);
    }
}

