package com.your_car_your_way.chat_api.service.auth.connection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.your_car_your_way.chat_api.model.LoginRequest;
import com.your_car_your_way.chat_api.model.User;
import com.your_car_your_way.chat_api.repository.UserInterface;
import com.your_car_your_way.chat_api.service.client_service.ClientService;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class ConnectionService implements ConnectionInterface {

    private List<User> users;
    private ClientService clientService;
    private UserInterface userInterface;

    ConnectionService(UserInterface userInterface){
        this.userInterface = userInterface;
    }

    // Méthode pour vérifier et connecter l'utilisateur
    @Override
    public User connecte(LoginRequest loginRequest){
        this.users = userInterface.loadUsers();
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
