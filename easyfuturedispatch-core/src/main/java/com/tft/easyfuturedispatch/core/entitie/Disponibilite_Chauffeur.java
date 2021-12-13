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

public class Disponibilite_Chauffeur {
    @Transient
    public static final String SEQUENCE_NAME = "disponibilite_chauffeur_sequence";
    @Id
    private Long id;
    private Chauffeur chauffeur;
    private Semaine_Jour semaine_jour;
    private String commentaire;
}
