package com.example.stationski.controllers;

import com.example.stationski.entities.*;
import com.example.stationski.services.ISkieurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/skieur")
public class SkieurController {
    ISkieurService skieurService;
    // http://localhost:8089/stationSki/skieur/assignSkieurToPiste/15/1
    @PutMapping("/assignSkieurToPiste/{numSkieur}/{numPiste}")
    public Skieur assignSkieurToPiste(@PathVariable("numSkieur")Long numSkieur,
                                        @PathVariable("numPiste")Long numPiste) {
        return skieurService.assignSkieurToPiste(numSkieur,numPiste);
    }

    // http://localhost:8089/stationSki/skieur/addSkieurAndAssignToCourse/120
    @PostMapping("/addSkieurAndAssignToCourse/{numCours}")
    public Skieur addSkieurAndAssignToCourse(@RequestBody Skieur skieur,
                                      @PathVariable("numCours")Long numCours) {
        return skieurService.addSkieurAndAssignToCourse(skieur,numCours);
    }


    // http://localhost:8089/stationSki/skieur/retrieveSkieursByTypeAbonnement/MENSUEL
    @GetMapping("/retrieveSkieursByTypeAbonnement/{typeAbonnement}")
    public List<Skieur> retrieveSkieursByTypeAbonnement(@PathVariable("typeAbonnement") TypeAbonnement typeAbonnement) {
        return skieurService.retrieveSkieursByTypeAbonnement(typeAbonnement);
    }

    // http://localhost:8089/stationSki/skieur/nombreSkieursParCouleurPiste
    @GetMapping("/nombreSkieursParCouleurPiste")
    public Map<Couleur, Integer> nombreSkieursParCouleurPiste() {
        return skieurService.nombreSkieursParCouleurPiste();
    }

}
