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
public class Citerne {
    @Transient
    public static final String SEQUENCE_NAME = "citerne_sequence";
    @Id
    private Long id;
    private String marque;
    private String assetId;
    private String description;
    private String manufacture;
    private String serialNumber;
    private String  modele;
    private String  matricule;
    private Long anneFabrication;
    private Long poids;
    private Long puissance;
    private boolean dispo;
    private boolean isAutoVireur;
    private boolean isVaccum;
    private boolean isCertificated;
    private boolean isBackCharge;
    private int dateEntretien;
    private int capacitePied;
    private int nbreCompartiment;

}
