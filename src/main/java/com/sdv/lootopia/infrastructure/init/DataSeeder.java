package com.sdv.lootopia.infrastructure.init;

import com.sdv.lootopia.domain.model.*;
import com.sdv.lootopia.infrastructure.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedDatabase(
            JpaUtilisateurRepository utilisateurRepo,
            JpaChasseRepository chasseRepo,
            JpaCacheRepository cacheRepo,
            JpaTransactionRepository transactionRepo,
            JpaEtapeRepository etapeRepo,
            JpaCreusageRepository creusageRepo,
            JpaParticipationRepository participationRepo,
            JpaProgressionRepository progressionRepo
    ) {
        return args -> {
            Utilisateur mircea = new Utilisateur();
            Utilisateur vlad = new Utilisateur();
            Utilisateur admin = new Utilisateur();

            mircea.setPseudo("wheremywagonat");
            mircea.setEmail("mcb@email.ro");
            mircea.setMotDePasse(new BCryptPasswordEncoder().encode("pass"));
            mircea.setCompteActif(true);
            mircea.setSoldeCouronnes(100.0);

            vlad.setPseudo("yawningBee");
            vlad.setEmail("vp@email.md");
            vlad.setMotDePasse(new BCryptPasswordEncoder().encode("pass1"));
            vlad.setCompteActif(true);
            vlad.setSoldeCouronnes(50.0);

            admin.setEmail("admin@lootopia.com");
            admin.setPseudo("admin");
            admin.setMotDePasse(new BCryptPasswordEncoder().encode("admin"));
            admin.setCompteActif(true);
            admin.setRole(Utilisateur.Role.ADMIN);

            utilisateurRepo.saveAll(List.of(mircea, vlad, admin));

            Chasse chasse1 = new Chasse();
            Chasse chasse2 = new Chasse();
            Chasse chasse3 = new Chasse();
            Chasse chasse4 = new Chasse();
            Chasse chasse5 = new Chasse();
            Chasse chasse6 = new Chasse();

            chasse1.setDescription("Trouve le coffre caché");
            chasse1.setTitre("Chasse au trésor 1");
            chasse1.setOrganisateur(vlad);
            chasse1.setMaxParticipants(6);
            chasse1.setFraisParticipation(25.0);
            chasse1.setTypeMonde(Chasse.TypeMonde.CARTOGRAPHIQUE);
            chasse1.setDateDebut(LocalDateTime.now().minusDays(2));
            chasse1.setDateFin(LocalDateTime.now().plusDays(2));
            chasse1.setStatut(Chasse.Statut.Active);

            chasse2.setDescription("Chasse des ruines");
            chasse2.setTitre("Mystère archéologique");
            chasse2.setOrganisateur(vlad);
            chasse2.setMaxParticipants(10);
            chasse2.setFraisParticipation(0.0);
            chasse2.setTypeMonde(Chasse.TypeMonde.REEL);
            chasse2.setDateDebut(LocalDateTime.now().minusDays(6));
            chasse2.setDateFin(LocalDateTime.now().plusDays(1));
            chasse2.setStatut(Chasse.Statut.Active);

            chasse3.setDescription("Chasse3");
            chasse3.setTitre("Chasse 3");
            chasse3.setOrganisateur(mircea);
            chasse3.setMaxParticipants(12);
            chasse3.setFraisParticipation(30.0);
            chasse3.setTypeMonde(Chasse.TypeMonde.CARTOGRAPHIQUE);
            chasse3.setDateDebut(LocalDateTime.now().minusDays(2));
            chasse3.setDateFin(LocalDateTime.now().plusDays(2));
            chasse3.setStatut(Chasse.Statut.Active);

            chasse4.setDescription("Chasse 4");
            chasse4.setTitre("Mystère 4");
            chasse4.setOrganisateur(vlad);
            chasse4.setMaxParticipants(8);
            chasse4.setFraisParticipation(10.0);
            chasse4.setTypeMonde(Chasse.TypeMonde.REEL);
            chasse4.setDateDebut(LocalDateTime.now().minusDays(6));
            chasse4.setDateFin(LocalDateTime.now().plusDays(4));
            chasse4.setStatut(Chasse.Statut.Active);

            chasse5.setDescription("Trouve le coffre caché 5");
            chasse5.setTitre("Chasse au trésor 5");
            chasse5.setOrganisateur(mircea);
            chasse5.setMaxParticipants(15);
            chasse5.setFraisParticipation(20.0);
            chasse5.setTypeMonde(Chasse.TypeMonde.CARTOGRAPHIQUE);
            chasse5.setDateDebut(LocalDateTime.now().minusDays(3));
            chasse5.setDateFin(LocalDateTime.now().plusDays(4));
            chasse5.setStatut(Chasse.Statut.Active);

            chasse6.setDescription("Chasse des ruines 6");
            chasse6.setTitre("Mystère archéologique 6");
            chasse6.setOrganisateur(vlad);
            chasse6.setMaxParticipants(50);
            chasse6.setFraisParticipation(25.0);
            chasse6.setTypeMonde(Chasse.TypeMonde.REEL);
            chasse6.setDateDebut(LocalDateTime.now().minusDays(5));
            chasse6.setDateFin(LocalDateTime.now().plusDays(2));
            chasse6.setStatut(Chasse.Statut.Active);
            chasseRepo.saveAll(List.of(chasse1, chasse2, chasse3, chasse4, chasse5, chasse6));

            Cache r1 = new Cache(null,45.123456, 4.567890, 200.0, "Felicitations", chasse1, Cache.TypeRecompense.COURONNES);
            Cache r2 = new Cache(null, 48.312, 23.123, 300.0, "Bravo", chasse2, Cache.TypeRecompense.COURONNES);
            Cache r3 = new Cache(null,45.123456, 4.567890, 250.0, "Felicitations", chasse3, Cache.TypeRecompense.COURONNES);
            Cache r4 = new Cache(null, 48.312, 23.123, 500.0, "Bravo", chasse4, Cache.TypeRecompense.COURONNES);
            Cache r5 = new Cache(null,45.123456, 4.567890, 200.0, "Felicitations", chasse5, Cache.TypeRecompense.COURONNES);
            Cache r6 = new Cache(null, 48.312, 23.123, 1000.0, "Bravo", chasse6, Cache.TypeRecompense.COURONNES);
            cacheRepo.saveAll(List.of(r1, r2, r3, r4, r5, r6));

            TransactionCouronnes t1 = new TransactionCouronnes(null, mircea, 20.0, TransactionCouronnes.TypeOperation.CREDIT, "gain chasse", LocalDateTime.now());
            TransactionCouronnes t2 = new TransactionCouronnes(null, vlad, -10.0, TransactionCouronnes.TypeOperation.DEBIT, "CREUSAGE", LocalDateTime.now());
            transactionRepo.saveAll(List.of(t1, t2));

            Participation p1 = new Participation(null, false, 1, false, null,null, mircea, chasse1, LocalDateTime.now().minusDays(1), Participation.Statut.ACTIF);
            Participation p2 = new Participation(null, true, -1, false, null, null, vlad, chasse2, LocalDateTime.now().minusDays(2), Participation.Statut.ACTIF);
            participationRepo.saveAll(List.of(p1, p2));

            Etape e1 = new Etape(null,1, "etape 1", null, null, "passphrase1",50.0, chasse1);
            Etape e2 = new Etape(null, 2, "etape 2", null, null, "passphrase2",150, chasse1);
            etapeRepo.saveAll(List.of(e1, e2));

            Progression pr1 = new Progression(null, true, p1, e1, LocalDateTime.now().minusHours(3));
            Progression pr2 = new Progression(null, false, p1, e2, null);
            progressionRepo.saveAll(List.of(pr1, pr2));

            Creusage c1 = new Creusage(null, p2, LocalDateTime.now().minusHours(2), false, 0.0);
            Creusage c2 = new Creusage(null, p2, LocalDateTime.now().minusHours(1), true, 5.0);
            creusageRepo.saveAll(List.of(c1, c2));

        };
    }
}