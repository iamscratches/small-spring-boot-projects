package com.iamscratches.spring.springbootdemo.webservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iamscratches.spring.springbootdemo.business.RunnerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payloadSend")
public class AsyncPayloadController {

    private final RunnerService runnerService;

    public AsyncPayloadController(RunnerService runnerService) {
        this.runnerService = runnerService;
    }

    @GetMapping
    public String sendPayload(){
        try {
            runnerService.send();
            return "payload send success";
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return "payload send failure";
        }
    }

}
