package com.frontend.TMSFrontend.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClientErrorHandler {
    public static ResponseMapper errorConverter(String exception) throws JsonProcessingException {
        String jsonError = exception;
        jsonError = jsonError.substring(jsonError.indexOf("{"), jsonError.length()-1);
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseMapper mapper = objectMapper.readValue(jsonError, ResponseMapper.class);
        return mapper;
    }
}
