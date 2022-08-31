package com.iamscratches.TMS.RESTControllers;

import com.iamscratches.TMS.model.Owner;
import com.iamscratches.TMS.service.OwnerService;
import com.iamscratches.TMS.utils.model.ResponseMapper;
import com.iamscratches.TMS.utils.model.Owner.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {
    public static final Logger LOGGER = LoggerFactory.getLogger(OwnerController.class);
    private final OwnerService service;

    @Autowired
    public OwnerController(OwnerService service) {
        LOGGER.debug("OwnerController Autowired with OwnerService");
        this.service = service;
    }

    /*
     * Get all Owner list
     */
    @GetMapping(value = "/ownerlist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMapper> getAllOwners(){
        LOGGER.debug("Recieved Owner List request");
        ResponseMapper mapper = service.getAllOwners();
        LOGGER.debug("getAllOwners: " + mapper.getValue());
        ResponseEntity<ResponseMapper> entity = new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
        LOGGER.debug("Send Owner List");
        return entity;
    }

    @PostMapping(value = "/{ID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMapper> getOwner(int id){
        LOGGER.debug("Received Owner ID info request");
        ResponseMapper mapper = service.getOwnerById(id);
        LOGGER.debug("Owner : " + mapper.getValue());
        ResponseEntity<ResponseMapper> entity = new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
        LOGGER.debug("Send Owner info details");
        return entity;
    }

    @PostMapping(value = "/import", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMapper> insertOwner(@RequestBody Owner owner){
        LOGGER.debug("Recieved Owner insert request" + owner);
        ResponseMapper mapper = service.insertOwner(owner, false);
        return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
    }

    @PutMapping(value = "/import", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMapper> updateOwner(@RequestBody Owner owner){
        LOGGER.debug("Recieved Owner update request" + owner);
        ResponseMapper mapper = service.insertOwner(owner, true);
        return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ResponseMapper> deleteOwner(
            @Valid @RequestParam(value="identifierType") Identifier identifierType,
            @RequestParam(value="identifierValue")String identifierValue){
        LOGGER.debug("Recieved Vehicle Type delete request");
        ResponseMapper mapper;
        if(identifierType == Identifier.OWNER_ID){
            mapper = service.deleteOwnerById(Integer.parseInt(identifierValue));
            return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
        }
        else if(identifierType == Identifier.MOBILE_NO){
            mapper = service.deleteByContactNo(Long.parseLong(identifierValue));
            return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
        }
        else if(identifierType == Identifier.ADHAAR){
            mapper = service.deleteByAdhaarNo(identifierValue);
            return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
        }
        else if(identifierType == Identifier.PANCARD){
            mapper = service.deleteByPancardNo(identifierValue);
            return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<ResponseMapper> findOwner(@RequestParam(value = "ID") int ID) throws NoSuchElementException {
        LOGGER.debug("Received search request for Owwner ID " + ID);
        ResponseMapper mapper = service.getOwnerById(ID);
        return new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
    }

}