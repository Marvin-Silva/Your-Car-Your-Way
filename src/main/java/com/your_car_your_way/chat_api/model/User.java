package com.your_car_your_way.chat_api.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

   private int id;
   private String nom;
   private String prenom;
   private Date dateNaissance;
   private String adresse;
   private String email;
   private String login;
   private String motDePasse;

   public User(){}
}
