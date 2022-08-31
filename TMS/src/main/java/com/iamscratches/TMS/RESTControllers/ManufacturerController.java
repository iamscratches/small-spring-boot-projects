package com.iamscratches.TMS.RESTControllers;

import com.iamscratches.TMS.model.Manufacturer;
import com.iamscratches.TMS.model.Owner;
import com.iamscratches.TMS.service.ManufacturerService;
import com.iamscratches.TMS.service.ManufacturerService;
import com.iamscratches.TMS.utils.model.Owner.Identifier;
import com.iamscratches.TMS.utils.model.ResponseMapper;
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
@RequestMapping("/api/manufacturer")
public class ManufacturerController {
    public static final Logger LOGGER = LoggerFactory.getLogger(ManufacturerController.class);
    private final ManufacturerService service;

    @Autowired
    public ManufacturerController(ManufacturerService service) {
        LOGGER.debug("ManufacturerController Autowired with ManufacturerService");
        this.service = service;
    }

    /*
     * Get all manufacturer list
     */
    @GetMapping(value = "/manufacturerlist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMapper> getAllManufacturers(){
        LOGGER.debug("Recieved Manufacturer List request");
        ResponseMapper mapper = service.getAllManufacturers();
        LOGGER.debug("getAllManufacturers: " + mapper.getValue());
        ResponseEntity<ResponseMapper> entity = new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
        LOGGER.debug("Send Manufacturer List");
        return entity;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<ResponseMapper> findManufacturer(@RequestParam(value = "ID") int ID) throws NoSuchElementException {
        LOGGER.debug("Received search request for Manufacturer ID " + ID);
        ResponseMapper mapper = service.getManufacturerById(ID);
        return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
    }

    @PutMapping(value = "/import", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMapper> insertManufacturer(@RequestBody Manufacturer manufacturer){
        LOGGER.debug("Recieved manufacturer insert request" + manufacturer);
        ResponseMapper mapper = service.insertManufacturer(manufacturer);
        return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
    }


    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ResponseMapper> deleteManufacturer(@RequestParam(value="ID") Integer manufacturerId){
        LOGGER.debug("Recieved manufacturer Type delete request");
        ResponseMapper mapper = service.deleteManufacturerById(manufacturerId);
        return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
    }

    @GetMapping(value = "/helloworld",produces = MediaType.APPLICATION_JSON_VALUE)
    public String HelloWorld(){
        return "Hello world";
    }
}
