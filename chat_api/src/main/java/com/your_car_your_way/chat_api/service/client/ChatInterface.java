package com.your_car_your_way.chat_api.service.client;

import java.util.List;

import org.springframework.stereotype.Service;

import com.your_car_your_way.chat_api.model.ServiceClient;

@Service
public interface ChatInterface {

    public List<ServiceClient> sendMessage(ServiceClient serviceClient);
    public List<ServiceClient> loadMessage();
}
