package com.iamscratches.TMS.RESTControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.iamscratches.TMS.model.Vehicle;
import com.iamscratches.TMS.model.vehicle.VehiclesUpdateList;
import com.iamscratches.TMS.service.VehicleService;
import com.iamscratches.TMS.utils.JsonMapper;
import com.iamscratches.TMS.utils.model.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
    public static final Logger LOGGER = LoggerFactory.getLogger(VehicleController.class);
    private final VehicleService service;

    @Autowired
    public VehicleController(VehicleService service) {
        LOGGER.debug("VehicleController Autowired with VehicleService");
        this.service = service;
    }

    /*
     * Get all vehicle list
     */
    @GetMapping(value = "/vehiclelist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMapper> getAllVehicles(){
        LOGGER.debug("Recieved Vehicle List request");
        ResponseMapper mapper = service.getAllVehicles();
        LOGGER.debug("getAllVehicles: " + mapper.getValue());
        ResponseEntity<ResponseMapper> entity = new ResponseEntity<>(mapper, mapper.getResponse().getResponseCode());
        LOGGER.debug("Send Vehicle List");
        return entity;
    }

    /*
     * Insert vehicle info without altering existing entries
     */
    @PostMapping(value = "/import", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public VehiclesUpdateList importVehicles(@RequestBody List<Vehicle> vehicleList){
        LOGGER.debug(String.valueOf(vehicleList));
        VehiclesUpdateList response = service.importVehicles(vehicleList, true);
        response.putTopStats("response", HttpStatus.CREATED.value());

        return response;
    }

    /*
     * Insert vehicle info modifying any existing existing entries
     */
    @PatchMapping(value = "/import", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public VehiclesUpdateList updateBulkVehicleEntry(@RequestBody List<Vehicle> vehicleList){
        LOGGER.debug(String.valueOf(vehicleList));
        VehiclesUpdateList response = service.importVehicles(vehicleList, false);
        response.putTopStats("response", HttpStatus.CREATED.value());
        return response;
    }

    /*
     * Insert single vehicle entry if vehicle ID not already exists
     */
    @PutMapping(value = "/import", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String[] vehicleEntry(@RequestBody Vehicle vehicle){
        LOGGER.debug(String.valueOf(vehicle));
        return service.importSingleVehicle(vehicle);
    }

    /*
     * Find single vehicle entry if vehicle ID already exists
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public Vehicle findVehicle(@RequestParam(value = "ID") int ID) throws NoSuchElementException{
        LOGGER.debug("Received search request for vehicle ID " + ID);
        Vehicle vehicle = service.getVehicleByID(ID).orElseThrow(() ->
                new NoSuchElementException("no corresponding Vehicle ID found"));
        return vehicle;
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public String deleteVehicle(@RequestParam(value = "ID") int ID) throws NoSuchElementException, JsonProcessingException {
        LOGGER.debug("Received delete request for vehicle ID " + ID);
        String response = service.deleteVehicleID(ID);
        LOGGER.debug(response);
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JsonMappingException.class)
    public String return400(JsonMappingException ex) throws JsonProcessingException {
        LOGGER.error("Unable to complete transactions", ex);
        return JsonMapper.mapToJson(new AbstractMap.SimpleEntry<>("message","JSON Validation failed!!"));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return404(NoSuchElementException ex) throws JsonProcessingException {
        LOGGER.error("Unable to complete transaction:" + ex.getMessage());
        return JsonMapper.mapToJson(new AbstractMap.SimpleEntry<>("message",ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String return404(HttpMessageNotReadableException ex) throws JsonProcessingException {
        LOGGER.error("Unable to complete transaction:" + ex.getMessage());
        return JsonMapper.mapToJson(new AbstractMap.SimpleEntry<>("message", "JSON Mapping failed!!") {
        });
    }
}
