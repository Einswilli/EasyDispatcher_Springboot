package com.tft.easyfuturedispatch.core.entitie;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Convoi {
    @Transient
    public static final String SEQUENCE_NAME = "convoi_sequence";
    @Id
    private Long id;
    private Localisation adresseDepart;
    private Localisation adresseDestination;
    private Chauffeur chauffeur;
    private Camion camion;
    private Citerne citerne;
    private Commande commande;
    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateDepart;
}
