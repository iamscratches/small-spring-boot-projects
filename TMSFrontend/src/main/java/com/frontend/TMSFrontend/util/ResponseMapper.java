package com.frontend.TMSFrontend.util;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.http.HttpStatus;

public class ResponseMapper {
    @JsonProperty("response")
    ResponseBlock response;
    @JsonProperty("value")
    Object value;

    public class ResponseBlock{
        private HttpStatus responseCode;
        private String message;

        public ResponseBlock() {
        }

        public ResponseBlock(HttpStatus responseCode, String message) {
            this.responseCode = responseCode;
            this.message = message;
        }

        public HttpStatus getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(HttpStatus responseCode) {
            this.responseCode = responseCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public ResponseMapper() {
    }

    public ResponseMapper(HttpStatus responseCode, String message){
        response = new ResponseBlock(responseCode, message);
    }

    public ResponseMapper(HttpStatus responseCode, String message, Object valueObject){
        response = new ResponseBlock(responseCode, message);
        value = valueObject;
    }

    @JsonGetter
    public ResponseBlock getResponse() {
        return response;
    }

    @JsonSetter
    public void setResponse(ResponseBlock response) {
        this.response = response;
    }

    @JsonGetter
    public Object getValue() {
        return value;
    }

    @JsonSetter
    public void setValue(Object value) {
        this.value = value;
    }


}
