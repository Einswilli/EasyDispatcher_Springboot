package com.tft.easyfuturedispatch.core.entitie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@AllArgsConstructor @NoArgsConstructor
@Data
public class Chauffeur {
    @Transient
    public static final String SEQUENCE_NAME = "chauffeur_sequence";
    @Id
    private Long id;
    private String nomChauffeur;
    private String prenomChauffeur;
    private Date date_embauche;
    private Date date_depart;
    private Statut_Chauffeur statut_chauffeur;
}
