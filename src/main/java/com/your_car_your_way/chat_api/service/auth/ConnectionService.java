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
    public List<User>loadUsers() throws StreamReadException, DatabindException, IOException{
        Resource resource = resourceLoader.getResource("classpath:users.json");
        ObjectMapper mapper = new ObjectMapper();
        users = mapper.readValue(resource.getInputStream(), new TypeReference<List<User>>(){});
        System.out.println("liste: "+users);
        return users;
    }
    
    // Méthode pour vérifier et connecter l'utilisateur
    public String connecte(String login, String password) throws IOException{
       for(User user: users){
        if ((user.getLogin().equals(login)) && (user.getMotDePasse().equals(password))) {
            ClientService.identifier = user.getId();
            // Get user identifier for service Client request 
            return "You are logged in as : " + login;
        }
       } 
       return "You are not logged !!!";
        }

    }
