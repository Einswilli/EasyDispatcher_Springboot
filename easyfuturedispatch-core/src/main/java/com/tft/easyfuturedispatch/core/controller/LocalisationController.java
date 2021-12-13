package com.tft.easyfuturedispatch.core.controller;

import com.tft.easyfuturedispatch.core.dao.LocalisationRepository;
import com.tft.easyfuturedispatch.core.entitie.Localisation;
import com.tft.easyfuturedispatch.core.exception.ResourceNotFoundException;
import com.tft.easyfuturedispatch.core.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/easyfuturedispatch/api/v1")
@RestController
public class LocalisationController {
    @Autowired
    private LocalisationRepository localisationRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @GetMapping("/localisation")
    public List<Localisation> index(){
        return localisationRepository.findAll();
    }
    @GetMapping("/localisation/{id}")
    public Localisation find(@PathVariable("id") long id) throws ResourceNotFoundException{
        return  localisationRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Aucun element trouver"));
    }
    @PostMapping("/localisation")
    public Localisation store(@RequestBody Localisation localisation){
        localisation.setId(sequenceGeneratorService.generateSequence(localisation.SEQUENCE_NAME));
        return localisationRepository.save(localisation);
    }
    @PutMapping("/localisation/{id}")
    public ResponseEntity<Localisation> update(@PathVariable("id") long id, @RequestBody Localisation localisation) throws ResourceNotFoundException{
        Localisation l = localisationRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Aucun element trouver"));
        l.setAdresse(localisation.getAdresse());
        l.setAdresseComplete(localisation.getAdresseComplete());
        l.setLattitude(localisation.getLattitude());
        l.setLongitude(localisation.getLongitude());
        final Localisation l1 = localisationRepository.save(l);
        return ResponseEntity.ok(l1);
    }
}
