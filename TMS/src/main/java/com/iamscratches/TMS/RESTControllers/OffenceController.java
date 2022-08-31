package com.iamscratches.TMS.RESTControllers;

import com.iamscratches.TMS.model.Offence;
import com.iamscratches.TMS.model.Roles;
import com.iamscratches.TMS.service.OffenceService;
import com.iamscratches.TMS.service.RolesService;
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
@RequestMapping("/api/offence")
public class OffenceController {
    public static final Logger LOGGER = LoggerFactory.getLogger(OffenceController.class);
    private final OffenceService service;

    @Autowired
    public OffenceController(OffenceService service) {
        LOGGER.debug("OffenceController Autowired with OffenceService");
        this.service = service;
    }

    /*
     * Get all users list
     */
    @GetMapping(value = "/offencelist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMapper> getAllOffence() {
        LOGGER.debug("Recieved Offence List request");
        ResponseMapper mapper = service.getAllOffence();
        LOGGER.debug("getAllOffence: " + mapper.getValue());

        ResponseEntity<ResponseMapper> entity = new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
        LOGGER.debug("Send Offence List");
        return entity;
    }

    @PutMapping(value = "/import", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMapper> insertManufacturer(@RequestBody Offence offence){
        LOGGER.debug("Recieved Offence insert request : " + offence);
        ResponseMapper mapper = service.insertOffence(offence);
        return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ResponseMapper> deleteUser(
            @RequestParam(value="offenceID") Integer offenceID){
        LOGGER.debug("Recieved offence delete request");
        ResponseMapper mapper = service.deleteOffenceById(offenceID);
        return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<ResponseMapper> findOffence(@RequestParam(value = "ID") int ID) throws NoSuchElementException {
        LOGGER.debug("Received search request for Offence ID " + ID);
        ResponseMapper mapper = service.getOffenceById(ID);
        return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
    }
}