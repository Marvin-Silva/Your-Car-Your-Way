package com.your_car_your_way.chat_api.controller.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.your_car_your_way.chat_api.model.LoginRequest;
import com.your_car_your_way.chat_api.model.User;
import com.your_car_your_way.chat_api.repository.UserInterface;
import com.your_car_your_way.chat_api.service.auth.connection.ConnectionInterface;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(path = "chat/api")
public class ConnectionController {

    private final ConnectionInterface connectionInterface;
    private final UserInterface userInterface;

    public ConnectionController(UserInterface userInterface, ConnectionInterface connectionInterface){
        this.userInterface = userInterface;
        this.connectionInterface = connectionInterface;
    }
    @PostMapping("/auth/user")
    public User getUser(@RequestBody LoginRequest loginRequest) {
        userInterface.loadUsers();
     return connectionInterface.connecte(loginRequest);
     
    }

    @GetMapping("/users")
    public List<User> getMethodName() {
        return userInterface.loadUsers();
    } 
    
}
