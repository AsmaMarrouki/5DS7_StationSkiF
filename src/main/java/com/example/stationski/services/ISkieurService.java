package com.example.stationski.services;

import com.example.stationski.entities.Couleur;
import com.example.stationski.entities.Skieur;
import com.example.stationski.entities.TypeAbonnement;

import java.util.EnumMap;
import java.util.List;

public interface ISkieurService {

    Skieur assignSkieurToPiste(Long numSkieur, Long numPiste);

    Skieur addSkieurAndAssignToCourse(Skieur skieur, Long numCourse);

    List<Skieur> retrieveSkieursByTypeAbonnement(TypeAbonnement typeAbonnement);

    EnumMap<Couleur, Integer> nombreSkieursParCouleurPiste();


}
