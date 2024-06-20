package com.your_car_your_way.chat_api.service.auth;
import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.your_car_your_way.chat_api.model.LoginRequest;
import com.your_car_your_way.chat_api.model.User;
import com.your_car_your_way.chat_api.service.client_service.ClientService;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class ConnectionService {

    private final ResourceLoader resourceLoader;
    private List<User> users;
    private ClientService clientService;

    public ConnectionService(ResourceLoader resourceLoader) throws StreamReadException, DatabindException, IOException{
        this.resourceLoader = resourceLoader;
    }

    // Methode pour charger les utilisateurs
    public List<User>loadUsers(){
        try{
        Resource resource = resourceLoader.getResource("classpath:users.json");
        ObjectMapper mapper = new ObjectMapper();
        users = mapper.readValue(resource.getInputStream(), new TypeReference<List<User>>(){});
        System.out.println("liste: "+users);
        }catch(Exception exception){
            exception.getStackTrace();
        }
        return users;
    }
    
    // Méthode pour vérifier et connecter l'utilisateur
    public User connecte(LoginRequest loginRequest){
    try{
        for(User user: users){
            if ((user.getLogin().equals(loginRequest.getLogin())) && (user.getMotDePasse().equals(loginRequest.getPassword()))) {
                // Get user identifier for service Client request 
                return user;
            }
           } 
           
        }catch(Error e){
            e.getStackTrace();
        }
        return null;
    }       
    }
