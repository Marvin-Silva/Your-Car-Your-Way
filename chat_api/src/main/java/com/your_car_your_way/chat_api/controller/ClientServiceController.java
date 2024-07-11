package com.your_car_your_way.chat_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.your_car_your_way.chat_api.model.ServiceClient;
import com.your_car_your_way.chat_api.service.client.ChatInterface;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(path="client-service/api")
public class ClientServiceController {

    private ChatInterface chatInterface;

    public ClientServiceController(ChatInterface chatInterface){
        this.chatInterface = chatInterface;
    }

    @GetMapping("/messages")
    public List<ServiceClient>getServiceClientMessage() throws StreamReadException, DatabindException, IOException {
        List<ServiceClient> serviceClient = chatInterface.loadMessage();
        return serviceClient;
    }

    @PostMapping("send-message")
    public List<ServiceClient> postMethodName(@RequestBody ServiceClient serviceClient) throws IOException {
        //TODO: process POST request
        List<ServiceClient> serviceClients = chatInterface.sendMessage(serviceClient);
        return serviceClients;
    } 
}
