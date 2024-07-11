package com.your_car_your_way.chat_api.repository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.your_car_your_way.chat_api.model.User;

@Service
public interface UserInterface {
public List<User>loadUsers();
}
