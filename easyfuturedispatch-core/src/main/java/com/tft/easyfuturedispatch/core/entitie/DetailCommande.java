package com.tft.easyfuturedispatch.core.entitie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor @NoArgsConstructor
@Data


public class DetailCommande {
    @Transient
    public static final String SEQUENCE_NAME = "detail_commande_sequence";
    @Id
    private Long id;
    private Produit produit;
    private Commande commande;
    private Commande qte_normal_expedie;
    private Commande qte_degel_expedie;
    private Commande prix_chauffeur;
    private Commande prix_voiturier;
    private Commande id_Type_silo_chargement;
    private Commande id_Type_silo_dechargement;

}
