package com.your_car_your_way.chat_api.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ServiceClient {
   
    private int id;
    private int utilisateurID;
    private String typeContact;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateContact;
    private String status;
   
    public ServiceClient(){
    // Convertir LocalDate en Date
    LocalDate localDate = LocalDate.now();
    this.dateContact = Date.from(localDate.atStartOfDay(ZoneId.of("Europe/Paris")).toInstant());

    }
}
