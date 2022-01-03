package com.tft.easyfuturedispatch.core.entitie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.mapping.Document;
import com.tft.easyfuturedispatch.core.entitie.Produit;

import java.util.Date;

@Document
@AllArgsConstructor @NoArgsConstructor
@Data
public class Commande {
    @Transient
    public static final String SEQUENCE_NAME = "commande_sequence";
    @Id
    private Long id;
    private Long reference;
    private Client clientExpediteur;
    private Client clientDestinataire;
    private int qte_normal_expedie;
    private int qte_degel_expedie;
    private String temp_chargement_prevu;
    private double distance_expediteur_destinataire;
    private double prix_chauffeur;
    private double prix_voiturier;
    private Date date_chargement_prevu;
    private Date date_dechargement_prevu;
    private DateOperators.Hour heure_chargement_prevu;
    private DateOperators.Hour heure_dechargement_prevu;
    private String id_Type_silo_chargement;
    private String id_Type_silo_dechargement;
    private Produit produit;
}
