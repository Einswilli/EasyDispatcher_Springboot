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
public class Camion {
    @Transient
    public static final String SEQUENCE_NAME = "camion_sequence";
    @Id
    private Long id;
    private String marque;
    private String model;
    private String matricule;
    private String couleur;
    private String anneFabrication;
    private double poids;
    private String puissance;
    private String dateEntretien;
    private boolean disponible;
    private boolean pret;
    private Chauffeur chauffeur;
    private String assetId;
    private String description;
    private String manufacture;
    private String serialNumber;
    private boolean isUsDot;

}
