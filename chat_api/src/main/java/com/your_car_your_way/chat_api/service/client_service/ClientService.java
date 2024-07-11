package com.your_car_your_way.chat_api.service.client_service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import com.your_car_your_way.chat_api.service.auth.connection.ConnectionService;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class ClientService {
    private ResourceLoader resourceLoader;
    private ConnectionService connectionService;

    ClientService(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    public List<ServiceClient> loadMessage(){
        List<ServiceClient> serviceClientList = new ArrayList<>();
        
        try{
        Resource resource = resourceLoader.getResource("classpath:client-service.json");

          // Ensure that the input stream is read as UTF-8
        byte[] jsonData = resource.getInputStream().readAllBytes();
        String jsonContent = new String(jsonData, StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        serviceClientList = mapper.readValue(jsonContent, new TypeReference<List<ServiceClient>>(){});
        System.out.println("RESOURCE: "+ serviceClientList);
        }catch(StreamReadException se){
            se.getStackTrace();
        }catch(DatabindException de){
            de.getStackTrace();
        }catch(IOException e){
            e.getStackTrace();
        }
        return serviceClientList;
    }

    public List<ServiceClient> sendMessage(ServiceClient serviceClient){
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
            newServiceClient.setId(serviceClient.getId());
            newServiceClient.setMessage(serviceClient.getMessage());
            newServiceClient.setUtilisateurID(serviceClient.getUtilisateurID());
            newServiceClient.setDateContact(serviceClient.getDateContact());
            newServiceClient.setTypeContact(serviceClient.getTypeContact());
            newServiceClient.setStatus(serviceClient.getStatus());
        
           
            int max = 0;
            serviceClientArray = mapper.readValue(file, new TypeReference<List<ServiceClient>>(){});
                    max = newServiceClient.getId();
                for (ServiceClient clients : serviceClientArray) {
                    if (newServiceClient.getId() <= max || newServiceClient.getId() == clients.getId()) {
                        max++;
                        newServiceClient.setId(max);
                    }   
                }
            

            // initialiser newServiceClient avec les données nécessaires
            serviceClientArray.add(newServiceClient);

             // Écrire les données mises à jour dans le fichier avec le format UTF_8
             BufferedWriter writter = Files.newBufferedWriter(Paths.get(pathToCreateMessage), StandardCharsets.UTF_8);
             mapper.writeValue(writter, serviceClientArray);
            //  Files.write(Paths.get(pathToCreateMessage), jsonContent.getBytes(StandardCharsets.UTF_8));

        }catch(IOException e){
            e.printStackTrace();
        }
        return serviceClientArray;

    }
}
