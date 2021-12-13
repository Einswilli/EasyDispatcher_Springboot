package com.tft.easyfuturedispatch.core.controller;

import com.tft.easyfuturedispatch.core.dao.ChauffeurRepository;
import com.tft.easyfuturedispatch.core.entitie.Chauffeur;
import com.tft.easyfuturedispatch.core.entitie.Client;
import com.tft.easyfuturedispatch.core.exception.ResourceNotFoundException;
import com.tft.easyfuturedispatch.core.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/easyfuturedispatch/api/v1")
public class ChauffeurController {
    @Autowired
    private ChauffeurRepository chauffeurRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @GetMapping("/chauffeur")
    public List<Chauffeur> getChauffeur(){
        return chauffeurRepository.findAll();
    }
    @GetMapping("/chauffeur/{id}")
    public Chauffeur getDetailsChauffeur(@PathVariable("id") Long id) throws ResourceNotFoundException{
        return chauffeurRepository.findById(id).orElseThrow(()-> new ResourceAccessException("Chauffeur not found"));
    }
    @PostMapping("/chauffeur")
    public Chauffeur saveChauffeur(@RequestBody Chauffeur chauffeur){
        chauffeur.setId(sequenceGeneratorService.generateSequence(Chauffeur.SEQUENCE_NAME));
        return chauffeurRepository.save(chauffeur);
    }
    @PutMapping("/chauffeur/{id}")
    public ResponseEntity<Chauffeur> updateChauffeur(@PathVariable("id") Long id, @RequestBody Chauffeur chauffeur) throws ResourceNotFoundException{
        Chauffeur chauffeur1 = chauffeurRepository.findById(id).orElseThrow(() ->new ResourceAccessException("Chauffeur not found"));
        chauffeur1.setNomChauffeur(chauffeur.getNomChauffeur());
        chauffeur1.setPrenomChauffeur(chauffeur.getPrenomChauffeur());
        chauffeur1.setStatut_chauffeur(chauffeur.getStatut_chauffeur());
        chauffeur1.setDate_depart(chauffeur.getDate_depart());
        chauffeur1.setDate_embauche(chauffeur.getDate_embauche());
        final Chauffeur c = chauffeurRepository.save(chauffeur1);
        return ResponseEntity.ok(c);
    }
}
