package com.iamscratches.spring.springbootdemo.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iamscratches.spring.springbootdemo.async.AsyncPayload;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RunnerService{
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;


    public RunnerService(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void send() throws JsonProcessingException {
        int index = (int)((Math.random())*(28-1)) + 1;
        AsyncPayload payload = new AsyncPayload();
        payload.setId(index);
        payload.setModel("ROOM");
        rabbitTemplate.convertAndSend("operations", "iamscratches.room.cleaner", objectMapper.writeValueAsString(payload));
    }
}
