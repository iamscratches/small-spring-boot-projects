package com.iamscratches.TMS.RESTControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iamscratches.TMS.model.OffenceDetails;
import com.iamscratches.TMS.model.User;
import com.iamscratches.TMS.service.OffenceDetailsService;
import com.iamscratches.TMS.service.UserService;
import com.iamscratches.TMS.utils.model.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/offencedetails")
public class OffenceDetailsController {
    public static final Logger LOGGER = LoggerFactory.getLogger(OffenceDetailsController.class);
    private final OffenceDetailsService service;

    @Autowired
    public OffenceDetailsController(OffenceDetailsService service) {
        LOGGER.debug("OffenceDetailsController Autowired with OffenceDetailsService");
        this.service = service;
    }

    /*
     * Get all users list
     */
    @GetMapping(value = "/offencedetailslist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMapper> getAllOffenceDetails(){
        LOGGER.debug("Recieved OffenceDetails List request");
        ResponseMapper mapper = service.getAllOffenceDetails();
        LOGGER.debug("getAllOffenceDetails: " + mapper.getValue());

        ResponseEntity<ResponseMapper> entity = new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
        LOGGER.debug("Send OffenceDetails List");
        return entity;
    }

    @PutMapping(value = "/import", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMapper> insertOffenceDetails(@RequestBody OffenceDetails details){
        LOGGER.debug("Recieved OffenceDetails insert request : " + details);
        ResponseMapper mapper = service.insertOffenceDetails(details);
        return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ResponseMapper> deleteOffenceDetails(@RequestParam(value="appNo") Integer appNo){
        LOGGER.debug("Recieved offence details delete request");
        ResponseMapper mapper = service.deleteOffenceDetailsById(appNo);
        return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
    }
}