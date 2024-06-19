package com.your_car_your_way.chat_api.controller.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.your_car_your_way.chat_api.model.User;
import com.your_car_your_way.chat_api.service.auth.ConnectionService;

import jakarta.websocket.server.PathParam;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(path = "chat/api")
public class ConnectionController {

    private final ConnectionService connectionService;

    public ConnectionController(ConnectionService connectionService){
        this.connectionService = connectionService;
    }

    @GetMapping("auth/user/{login}/{password}")
    public String getMethodName(@PathVariable("login")String login, @PathVariable("password")String password) throws StreamReadException, DatabindException, IOException {
    List<User> user = connectionService.loadUsers();
    System.out.println(user);
     String con = connectionService.connecte(login, password);
     return con;
    }
    
}
