package com.example.iamscratches.guestservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class GuestNotFoundException extends RuntimeException {
    public GuestNotFoundException(String s) {
        super(s);
    }
}
