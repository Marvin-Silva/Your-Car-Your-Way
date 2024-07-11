package com.your_car_your_way.chat_api.service.auth.connection;

import org.springframework.stereotype.Service;

import com.your_car_your_way.chat_api.model.LoginRequest;
import com.your_car_your_way.chat_api.model.User;

@Service
public interface ConnectionInterface {
public User connecte(LoginRequest loginRequest);
}
