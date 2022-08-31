package com.iamscratches.TMS.RESTControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iamscratches.TMS.model.Registration;
import com.iamscratches.TMS.model.User;
import com.iamscratches.TMS.service.RegistrationService;
import com.iamscratches.TMS.service.UserService;
import com.iamscratches.TMS.utils.model.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {
    public static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
    private final RegistrationService service;

    @Autowired
    public RegistrationController(RegistrationService service) {
        LOGGER.debug("RegistrationController Autowired with RegistrationService");
        this.service = service;
    }

    /*
     * Get all users list
     */
    @GetMapping(value = "/registrationlist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMapper> getAllRegistration() throws JsonProcessingException {
        LOGGER.debug("Recieved Registration List request");
        ResponseMapper mapper = service.getAllRegistrations();
        LOGGER.debug("getAllRegistrations: " + mapper.getValue());

        ResponseEntity<ResponseMapper> entity = new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
        LOGGER.debug("Send Registrations List");
        return entity;
    }

    @PutMapping(value = "/import", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMapper> insertRegistration(@RequestBody Registration registration){
        LOGGER.debug("Recieved registration insert request : " + registration);
        ResponseMapper mapper = service.insertRegistration(registration);
        return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ResponseMapper> deleteUser(@RequestParam(value="appNo") Integer appNo){
        LOGGER.debug("Recieved registration delete request");
        ResponseMapper mapper = service.deleteByAppNo(appNo);
        return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<ResponseMapper> getRegistration(@RequestParam(value = "ID") int ID){
        LOGGER.debug("Received search request for Registration ID " + ID);
        ResponseMapper mapper = service.getRegistrationById(ID);
        return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
    }
}