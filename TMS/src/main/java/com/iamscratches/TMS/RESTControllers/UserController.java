package com.iamscratches.TMS.RESTControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iamscratches.TMS.model.Manufacturer;
import com.iamscratches.TMS.model.User;
import com.iamscratches.TMS.model.Vehicle;
import com.iamscratches.TMS.service.ManufacturerService;
import com.iamscratches.TMS.service.UserService;
import com.iamscratches.TMS.utils.JsonMapper;
import com.iamscratches.TMS.utils.model.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/user")
public class UserController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        LOGGER.debug("UserController Autowired with UserService");
        this.service = service;
    }

    /*
     * Get all users list
     */
    @GetMapping(value = "/userslist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMapper> getAllUsers() throws JsonProcessingException {
        LOGGER.debug("Recieved Users List request");
        ResponseMapper mapper = service.getAllUsers();
        LOGGER.debug("getAllUsers: " + mapper.getValue());

        ResponseEntity<ResponseMapper> entity = new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
        LOGGER.debug("Send Users List");
        return entity;
    }

    @PutMapping(value = "/import", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMapper> insertManufacturer(@RequestBody User user){
        LOGGER.debug("Recieved user insert request : " + user);
        ResponseMapper mapper = service.insertUser(user);
        return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ResponseMapper> deleteUser(@RequestParam(value="username") String username){
        LOGGER.debug("Recieved user delete request");
        ResponseMapper mapper = service.deleteUserById(username);
        return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<ResponseMapper> findUser(@RequestParam(value = "ID") String username) throws NoSuchElementException {
        LOGGER.debug("Received search request for User username " + username);
        ResponseMapper mapper = service.getUserByUsername(username);
        return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
    }
}