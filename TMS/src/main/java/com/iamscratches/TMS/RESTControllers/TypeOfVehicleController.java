package com.iamscratches.TMS.RESTControllers;

import com.iamscratches.TMS.model.TypeOfVehicles;
import com.iamscratches.TMS.service.TypeOfVehicleService;
import com.iamscratches.TMS.utils.model.ResponseMapper;
import com.iamscratches.TMS.utils.model.typeOfVehicles.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/tov")
public class TypeOfVehicleController {
    public static final Logger LOGGER = LoggerFactory.getLogger(TypeOfVehicleController.class);
    private final TypeOfVehicleService service;

    @Autowired
    public TypeOfVehicleController(TypeOfVehicleService service) {
        LOGGER.debug("TypeOfVehicleController Autowired with TypeOfVehicleService");
        this.service = service;
    }

    /*
     * Get all TypeOfVehicles list
     */
    @GetMapping(value = "/typeofvehicleslist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMapper> getAllTypeOfVehicles(){
        LOGGER.debug("Recieved Vehicle List request");
        ResponseMapper mapper = service.getAllTypesOfVehicles();
        LOGGER.debug("getAllTypeOfVehicles: " + mapper.getValue());
        LOGGER.debug("Send TypeOfVehicles List");
        ResponseEntity<ResponseMapper> entity = new ResponseEntity<>
                (mapper, mapper.getResponse().getResponseCode());
        return entity;
    }

    @PutMapping(value = "/import", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseMapper> vehicleEntry(@RequestBody TypeOfVehicles tov){
        LOGGER.debug("Recieved Vehicle Type insert request");
        ResponseMapper mapper = service.insertTOV(tov);
        ResponseEntity<ResponseMapper> entity = new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
        return entity;
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ResponseMapper> deleteVehicleType(
            @Valid @RequestParam(value="identifierType")Identifier identifierType,
            @RequestParam(value="identifierValue")String identifierValue){
        LOGGER.debug("Recieved Vehicle Type delete request");
        ResponseMapper mapper;
        if(identifierType == Identifier.VEHICLE_TYPE_ID){
            mapper = service.deleteByVehicleID(Integer.parseInt(identifierValue));
            return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
        }
        else if(identifierType == Identifier.VEHICLE_CATEGORY){
            mapper = service.deleteByVehicleCategory(identifierValue);
            return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<ResponseMapper> findTOV(@RequestParam(value = "ID") int ID) throws NoSuchElementException {
        LOGGER.debug("Received search request for TOV ID " + ID);
        ResponseMapper mapper = service.getTypeOfVehicleById(ID);
        return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
    }

}