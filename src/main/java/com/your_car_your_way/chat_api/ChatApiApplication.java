package com.your_car_your_way.chat_api;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

@SpringBootApplication
public class ChatApiApplication {

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		SpringApplication.run(ChatApiApplication.class, args);
	}

}
