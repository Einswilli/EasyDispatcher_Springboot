package com.tft.easyfuturedispatch.core.controller;

import com.tft.easyfuturedispatch.core.dao.CommandeRepository;
import com.tft.easyfuturedispatch.core.entitie.Commande;
import com.tft.easyfuturedispatch.core.exception.ResourceNotFoundException;
import com.tft.easyfuturedispatch.core.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/easyfuturedispatch/api/v1")
public class CommandeController {
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/commande")
    public List<Commande> index(){
        return commandeRepository.findAll();
    }
    @GetMapping("/commande/{id}")
    public Commande find(@PathVariable("id") long id) throws ResourceNotFoundException{
        return commandeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aucun élément trouvé"));
    }
    @PostMapping("/commande")
    public Commande store(@RequestBody Commande commande){
        commande.setId(sequenceGeneratorService.generateSequence(commande.SEQUENCE_NAME));
        return commandeRepository.save(commande);
    }
    @PutMapping("/commande/{id}")
    public ResponseEntity<Commande> update(@PathVariable("id") long id, @RequestBody Commande commande) throws ResourceNotFoundException{
        Commande c = commandeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Aucun élément trouvé"));
        c.setClientDestinataire(commande.getClientDestinataire());
        c.setClientExpediteur(commande.getClientExpediteur());
        c.setDate_chargement_prevu(commande.getDate_chargement_prevu());
        c.setDistance_expediteur_destinataire(commande.getDistance_expediteur_destinataire());
        c.setHeure_chargement_prevu(commande.getHeure_chargement_prevu());
        c.setHeure_dechargement_prevu(commande.getHeure_dechargement_prevu());
        c.setId_Type_silo_chargement(commande.getId_Type_silo_chargement());
        c.setId_Type_silo_dechargement(commande.getId_Type_silo_dechargement());
        c.setPrix_chauffeur(commande.getPrix_chauffeur());
        c.setPrix_voiturier(commande.getPrix_voiturier());
        c.setQte_degel_expedie(commande.getQte_degel_expedie());
        c.setQte_normal_expedie(commande.getQte_normal_expedie());
        c.setReference(commande.getReference());
        c.setTemp_chargement_prevu(commande.getTemp_chargement_prevu());
        final Commande commande1 = commandeRepository.save(c);
        return ResponseEntity.ok(commande1);
    }
    @DeleteMapping("/commande/{id}")
    public Map<String, Boolean> deleteCommand(@PathVariable(value = "id") Long commandId)
            throws ResourceNotFoundException {
        Commande command = commandeRepository.findById(commandId)
                .orElseThrow(() -> new ResourceNotFoundException("Command not found for this id :: " + commandId));

        commandeRepository.delete(command);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
