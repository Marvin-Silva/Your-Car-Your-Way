package com.your_car_your_way.chat_api.service.client_service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.your_car_your_way.chat_api.model.ServiceClient;
import com.your_car_your_way.chat_api.model.User;
import com.your_car_your_way.chat_api.service.auth.ConnectionService;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class ClientService {
    private ResourceLoader resourceLoader;
    private List<ServiceClient> serviceClientList;
    private ConnectionService connectionService;
    public static int identifier = 0;

    ClientService(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    public List<ServiceClient> loadMessage() throws StreamReadException, DatabindException, IOException{
        Resource resource = resourceLoader.getResource("classpath:client-service.json");
        ObjectMapper mapper = new ObjectMapper();
        serviceClientList = mapper.readValue(resource.getInputStream(), new TypeReference<List<ServiceClient>>(){});
        System.out.println("RESOURCE: "+ serviceClientList);
        return serviceClientList;
    }

    public List<ServiceClient> sendMessage(String message){
        // Path to write data
        String pathToCreateMessage = "src/main/resources/client-service.json";

        ObjectMapper mapper = new ObjectMapper();

        List<ServiceClient> serviceClientArray = new ArrayList<>();

        try{
            // Lire les données existantes du fichier
            File file = new File(pathToCreateMessage);
            if (file.exists() && !file.isDirectory()) {
                serviceClientArray = mapper.readValue(file, new TypeReference<List<ServiceClient>>(){});
            }

            // Ajouter les nouvelles données
            ServiceClient newServiceClient = new ServiceClient();
            newServiceClient.getId();
            newServiceClient.setMessage(message);
            newServiceClient.setUtilisateurID(identifier);

            int max = 0;
            serviceClientArray = mapper.readValue(file, new TypeReference<List<ServiceClient>>(){});
                    max = newServiceClient.getId();
                for (ServiceClient serviceClient : serviceClientArray) {
                    if (newServiceClient.getId() <= max) {
                        max++;
                        newServiceClient.setId(max);
                    }   
                }
            

            // initialiser newServiceClient avec les données nécessaires
            serviceClientArray.add(newServiceClient);

             // Écrire les données mises à jour dans le fichier
             FileWriter fileWriter = new FileWriter(pathToCreateMessage);
             mapper.writeValue(fileWriter, serviceClientArray);
             fileWriter.close();
                
        }catch(IOException e){
            e.printStackTrace();
        }
        return serviceClientArray;

    }
}
