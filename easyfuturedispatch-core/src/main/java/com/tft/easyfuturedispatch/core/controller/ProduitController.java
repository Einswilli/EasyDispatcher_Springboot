package com.tft.easyfuturedispatch.core.controller;
import com.tft.easyfuturedispatch.core.dao.ProduitRepository;
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
public class ProduitController {
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @GetMapping("/produit")
    public List<Produit> index(){
        return produitRepository.findAll();
    }
    @GetMapping("/produit/{id}")
    public Produit find(@PathVariable("id") long id) throws ResourceNotFoundException{
        return produitRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Aucun element trouver"));
    }
     @PostMapping("/produit")
    public Produit store(@RequestBody Produit produit){
        produit.setId(sequenceGeneratorService.generateSequence(produit.SEQUENCE_NAME));
        return produitRepository.save(produit);
    }
    @PutMapping("/produit/{id}")
    public ResponseEntity<Produit> update(@PathVariable("id") long id, @RequestBody Produit produit) throws ResourceNotFoundException{
        Produit p = produitRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Aucun element trouver"));
        p.setDescription(produit.getDescription());
        p.setDesignation(produit.getDesignation());
        final Produit updateProduit = produitRepository.save(p);
        return  ResponseEntity.ok(updateProduit);
    }


    @DeleteMapping("/deleteProduit/{id}")
    public Map<String, Boolean> deleteProduit(@PathVariable(value = "id") Long produitId)
            throws ResourceNotFoundException {
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new ResourceNotFoundException("Produit not found for this id :: " + produitId));
        produitRepository.delete(produit);
        Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }

}