package com.tft.easyfuturedispatch.core.controller;

import com.tft.easyfuturedispatch.core.dao.ConvoiRepository;
import com.tft.easyfuturedispatch.core.entitie.Convoi;
import com.tft.easyfuturedispatch.core.exception.ResourceNotFoundException;
import com.tft.easyfuturedispatch.core.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/easyfuturedispatch/api/v1")
public class ConvoiController {
    @Autowired
    private ConvoiRepository convoiRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @GetMapping("/convoi")
    public List<Convoi> index(){
        return convoiRepository.findAll();
    }
    @GetMapping("/convoi/{id}")
    public Convoi find(@PathVariable("id") long id) throws ResourceNotFoundException{
        return convoiRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Aucun element trouver"));
    }
    @PostMapping("/convoi")
    public Convoi store(@RequestBody Convoi convoi){
        convoi.setId(sequenceGeneratorService.generateSequence(convoi.SEQUENCE_NAME));
        return convoiRepository.save(convoi);
    }
    @PutMapping("/convoi/{id}")
    public ResponseEntity<Convoi> update(@PathVariable("id") long id, @RequestBody Convoi convoi) throws ResourceNotFoundException{
        Convoi c = convoiRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucun element trouver"));
        c.setAdresseDepart(convoi.getAdresseDepart());
        c.setAdresseDestination(convoi.getAdresseDestination());
        c.setCamion(convoi.getCamion());
        c.setChauffeur(convoi.getChauffeur());
        c.setCiterne(convoi.getCiterne());
        c.setCommande(convoi.getCommande());
        c.setDateDepart(convoi.getDateDepart());
        final Convoi c1 = convoiRepository.save(c);
        return ResponseEntity.ok(c1);
    }
}
