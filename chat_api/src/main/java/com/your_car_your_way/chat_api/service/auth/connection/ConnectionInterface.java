package com.your_car_your_way.chat_api.service.auth.connection;

import com.your_car_your_way.chat_api.model.LoginRequest;
import com.your_car_your_way.chat_api.model.User;

public interface ConnectionInterface {
public User connecte(LoginRequest loginRequest);
}
