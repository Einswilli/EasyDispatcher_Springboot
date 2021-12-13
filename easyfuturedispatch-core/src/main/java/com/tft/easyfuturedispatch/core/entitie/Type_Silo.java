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

public class Type_Silo {
    @Transient
    public static final String SEQUENCE_NAME = "type_silo_sequence";
    @Id
    private Long id;
    private String libelle_Type_Silo;
}
