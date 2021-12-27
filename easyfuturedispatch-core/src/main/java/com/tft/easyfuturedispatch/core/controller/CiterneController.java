package com.tft.easyfuturedispatch.core.controller;

import com.tft.easyfuturedispatch.core.dao.CiterneRepository;
import com.tft.easyfuturedispatch.core.entitie.Citerne;
import com.tft.easyfuturedispatch.core.entitie.Produit;
import com.tft.easyfuturedispatch.core.exception.ResourceNotFoundException;
import com.tft.easyfuturedispatch.core.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/easyfuturedispatch/api/v1")
public class CiterneController {
    @Autowired
    private CiterneRepository citerneRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/citerne")
    public List<Citerne> index (){
        return citerneRepository.findAll();
    }
    @GetMapping("/citerne/{id}")
    public Citerne find(@PathVariable("id") long id) throws ResourceNotFoundException{
        return citerneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucun element trouver"));
    }
    @PostMapping("/citerne")
    public Citerne store(@RequestBody Citerne citerne){
        citerne.setId(sequenceGeneratorService.generateSequence(citerne.SEQUENCE_NAME));
        return citerneRepository.save(citerne);
    }
    @PutMapping("/citerne/{id}")
    public ResponseEntity<Citerne> update(@PathVariable("id") long id, @RequestBody Citerne citerne) throws ResourceNotFoundException{
        Citerne c = citerneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucun element trouver"));
        c.setMarque(citerne.getMarque());
        c.setAnneFabrication(citerne.getAnneFabrication());
        c.setAssetId(citerne.getAssetId());
        c.setAutoVireur(citerne.isAutoVireur());
        c.setBackCharge(citerne.isBackCharge());
        c.setCertificated(citerne.isCertificated());
        c.setCapacitePied(citerne.getCapacitePied());
        c.setDateEntretien(citerne.getDateEntretien());
        c.setDispo(citerne.isDispo());
        c.setManufacture(citerne.getManufacture());
        c.setDescription(citerne.getDescription());
        c.setVaccum(citerne.isVaccum());
        c.setModele(citerne.getModele());
        c.setMatricule(citerne.getMatricule());
        c.setNbreCompartiment(citerne.getNbreCompartiment());
        c.setSerialNumber(citerne.getSerialNumber());
        c.setPuissance(citerne.getPuissance());
        final Citerne citerne1 = citerneRepository.save(c);
        return ResponseEntity.ok(citerne1);

    }

    @DeleteMapping("/deleteCiterne/{id}")
    public Map<String, Boolean> deleteCiterne(@PathVariable(value = "id") Long citerneId)
            throws ResourceNotFoundException {
        Citerne citerne = citerneRepository.findById(citerneId)
                .orElseThrow(() -> new ResourceNotFoundException("Citerne not found for this id :: " + citerneId));
        citerneRepository.delete(citerne);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }

}
