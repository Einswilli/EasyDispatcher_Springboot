package com.ftf.easyfuturedispatch.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@JsonIgnoreProperties(ignoreUnknown = true)
public class AppUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "app_user_name" , unique = true)
  private String appUserName;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Column(name = "app_user_password" , unique = true)
  private String appUserPassword;
  @Column(name = "app_user_is_activated" )
  private boolean appUserIsActivated;
 // @Column(name = "app_user_email" , unique = true)
 // private String appUserEmail;
  @Column(name = "app_user_first_name")
  private String appUserFirstName;
  @Column(name = "app_user_last_name")
  private String appUserLastName;

  @ManyToMany(fetch = FetchType.EAGER)
  private Collection<AppRole> roles =new ArrayList<>();

}
