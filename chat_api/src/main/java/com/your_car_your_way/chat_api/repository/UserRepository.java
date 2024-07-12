package com.your_car_your_way.chat_api.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.your_car_your_way.chat_api.model.User;

@Service
public class UserRepository implements UserInterface {
    private final ResourceLoader resourceLoader;
    private List<User> users = new ArrayList<>();

    public UserRepository(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }
    // Methode pour charger les utilisateurs
    @Override
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

}
