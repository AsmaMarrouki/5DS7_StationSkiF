package com.example.stationski.services;

import com.example.stationski.entities.*;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.repositories.PisteRepository;
import com.example.stationski.repositories.SkieurRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.EnumMap;


@Service
@AllArgsConstructor
@Slf4j
public class SkieurService implements ISkieurService{

    SkieurRepository skieurRepository;
    PisteRepository pisteRepository;
    CoursRepository coursRepository;
    @Transactional
    public Skieur assignSkieurToPiste(Long numSkieur, Long numPiste) {
        log.info("debut methode assignSkieurToPiste");
        Skieur skieur = skieurRepository.findByNumSkieur(numSkieur);
        Piste piste =pisteRepository.findByNumPiste(numPiste);
        log.info("skieur "+skieur.getNumSkieur());
        log.info("piste "+piste.getNomPiste());
        skieur.getPistes().add(piste);
        log.info("fin methode assignSkieurToPiste");

        return skieur;
    }

    @Transactional
    public Skieur addSkieurAndAssignToCourse(Skieur skieur, Long numCourse) {
        log.info("debut methode addSkieurAndAssignToCourse");

        Cours cours = coursRepository.findByNumCours(numCourse);
        Skieur s = skieurRepository.save(skieur);
        Set<Inscription> inscriptions = s.getInscriptions();

        inscriptions.forEach(inscription -> inscription.setCours(cours));

        log.info("fin methode addSkieurAndAssignToCourse");
        // t2= date sys - t1
        return null;
    }


    @Override
    public List<Skieur> retrieveSkieursByTypeAbonnement(TypeAbonnement typeAbonnement) {
        return skieurRepository.findByAbonnementTypeAbon(typeAbonnement);
    }

    @Override
    public EnumMap<Couleur, Integer> nombreSkieursParCouleurPiste() {
        log.info("debut methode nombreSkieursParCouleurPiste");
        EnumMap<Couleur, Integer> nombreSkieursParCouleurPiste = new EnumMap<>(Couleur.class);
        for(Couleur c: Couleur.values()) {
            nombreSkieursParCouleurPiste.put(c,skieurRepository.skieursByCouleurPiste(c).size());

        }
        log.info("fin methode nombreSkieursParCouleurPiste");

        return nombreSkieursParCouleurPiste;
    }


}
