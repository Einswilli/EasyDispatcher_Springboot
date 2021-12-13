package com.tft.easyfuturedispatch.core.controller;

import com.tft.easyfuturedispatch.core.dao.CamionRepository;
import com.tft.easyfuturedispatch.core.entitie.Camion;
import com.tft.easyfuturedispatch.core.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
@RestController
@RequestMapping("/easyfuturedispatch/api/v1")
public class CamionController {
    @Autowired
    private CamionRepository camionRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/camion")
    public List<Camion> index(){
        return camionRepository.findAll();
    }
    @GetMapping("/camion/{id}")
    public Camion find(@PathVariable("id") long id) throws ResourceAccessException{
        return camionRepository.findById(id).orElseThrow(()-> new ResourceAccessException("Aucun element trouvé"));
    }
    @PostMapping("/camion")
    public Camion store(@RequestBody Camion camion){
        camion.setId(sequenceGeneratorService.generateSequence(camion.SEQUENCE_NAME));
        return camionRepository.save(camion);
    }
    @PutMapping("/camion/{id}")
    public ResponseEntity<Camion> update(@PathVariable("id") long id, @RequestBody Camion camion){
        Camion camion1 = camionRepository.findById(id).orElseThrow(() -> new ResourceAccessException("Aucun element trouvé"));
        camion1.setAnneFabrication(camion.getAnneFabrication());
        camion1.setChauffeur(camion.getChauffeur());
        camion1.setCouleur(camion.getCouleur());
        camion1.setDateEntretien(camion.getDateEntretien());
        camion1.setDisponible(camion.isDisponible());
        camion1.setMarque(camion.getMarque());
        camion1.setMatricule(camion.getMatricule());
        camion1.setModele(camion.getModele());
        camion1.setPoids(camion.getPoids());
        camion1.setPret(camion.isPret());
        camion1.setPuissance(camion.getPuissance());
        camion1.setAssetId(camion.getAssetId());
        camion1.setDescription(camion.getDescription());
        camion1.setManufacture(camion.getManufacture());
        camion1.setUsDot(camion1.isUsDot());
        final Camion c = camionRepository.save(camion1);
        return  ResponseEntity.ok(c);
    }
}
