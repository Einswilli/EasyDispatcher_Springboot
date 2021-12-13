package com.tft.easyfuturedispatch.core.controller;

import com.tft.easyfuturedispatch.core.dao.DetailCommandeRepository;
import com.tft.easyfuturedispatch.core.entitie.DetailCommande;
import com.tft.easyfuturedispatch.core.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/easyfuturedispatch/api/v1")
@RestController
public class DetailCommandeController {
     @Autowired
    private DetailCommandeRepository detailCommandeRepository;
     @GetMapping("/detailscommande")
    public List<DetailCommande> index(){
         return  detailCommandeRepository.findAll();
     }
     @GetMapping("/detailscommande/{id}")
    public DetailCommande find(@PathVariable("id") Long id ) throws ResourceNotFoundException{
         return  detailCommandeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid parameter"));
     }
     @PutMapping("/detailscommande/{id}")
     public ResponseEntity<DetailCommande> update(@RequestBody DetailCommande detailCommande, @PathVariable("id") Long id) throws ResourceNotFoundException{
         DetailCommande d = detailCommandeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("invalid parameter"));
         d.setCommande(detailCommande.getCommande());
         d.setId_Type_silo_chargement(detailCommande.getId_Type_silo_chargement());
         d.setPrix_chauffeur(detailCommande.getPrix_chauffeur());
         d.setProduit(detailCommande.getProduit());
         d.setPrix_voiturier(detailCommande.getPrix_voiturier());
         d.setQte_degel_expedie(detailCommande.getQte_degel_expedie());
         d.setQte_normal_expedie(detailCommande.getQte_normal_expedie());
         d.setId_Type_silo_dechargement(detailCommande.getId_Type_silo_dechargement());

         DetailCommande d1 = detailCommandeRepository.save(d);
         return ResponseEntity.ok(d1);
     }
}
