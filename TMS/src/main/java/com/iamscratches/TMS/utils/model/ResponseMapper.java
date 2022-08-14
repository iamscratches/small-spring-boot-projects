package com.iamscratches.TMS.utils.model;

import org.springframework.http.HttpStatus;

public class ResponseMapper {
    ResponseBlock response;
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

    public ResponseMapper(HttpStatus responseCode,String message){
        response = new ResponseBlock(responseCode, message);
    }

    public ResponseMapper(HttpStatus responseCode,String message, Object valueObject){
        response = new ResponseBlock(responseCode, message);
        value = valueObject;
    }

    public ResponseBlock getResponse() {
        return response;
    }

    public void setResponse(ResponseBlock response) {
        this.response = response;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
