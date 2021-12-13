package com.tft.easyfuturedispatch.core.entitie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

@Document(collection = "Client")
@Data @AllArgsConstructor @NoArgsConstructor
public class Client {

  @Transient
  public static final String SEQUENCE_NAME = "users_sequence";

  @Id @BsonProperty("id")
  private long id;
  private String clientName;
  private String clientType;
  private int clientAdresseMunicipalNumber;
  private String clientAdresseStreetName;
  private String clientAdresseCity;
  private String clientAdresseCountry;
  private String clientPhoneNumber;
  //private String idClientPayer;
  private String clientAdresseComplete;
  // private String clientExpediteur;
  // private String clientDestinataire;
  // private String clientPayeur;

}
