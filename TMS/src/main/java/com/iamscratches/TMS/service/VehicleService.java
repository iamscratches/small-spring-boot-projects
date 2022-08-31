package com.iamscratches.TMS.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iamscratches.TMS.model.Roles;
import com.iamscratches.TMS.model.Vehicle;
import com.iamscratches.TMS.model.vehicle.VehiclesUpdateList;
import com.iamscratches.TMS.repo.VehicleRepository;
import com.iamscratches.TMS.utils.IdGenerator;
import com.iamscratches.TMS.utils.JsonMapper;
import com.iamscratches.TMS.utils.model.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class VehicleService {
    public static final Logger LOGGER = LoggerFactory.getLogger(VehicleService.class);

    private final VehicleRepository vehicleRepository;
    private static String totalEntries = "total entries";
    private static String entriesCreated = "entries created";
    private static String entriesAssigned = "entries assigned";
    private static String entriesIgnored = "entries ignored";
    private static String entriesModified = "modified entries";

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        LOGGER.debug("VehicleService Autowired with VehicleRepository");
        this.vehicleRepository = vehicleRepository;
    }

    public ResponseMapper getAllVehicles(){
        try{
            LOGGER.debug("Recieved Vehicle List request");
            Iterable<Vehicle> vehicles = this.vehicleRepository.findAll();
            List<Vehicle> vehicleList = new ArrayList<>();
            vehicles.forEach(vehicleList::add);

            LOGGER.debug("Send Vehicle List");
            return new ResponseMapper(HttpStatus.OK, "Fetched All Roles", vehicleList);
        }catch (Exception e){
            LOGGER.error("Vehicles fetch failed : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to fetch Roles details");
        }
    }

    public ResponseMapper importVehicles(List<Vehicle> vehicleList, boolean skipUpdate){

        Map<Integer, Vehicle> entriesCreatedList = new HashMap<>();
        Map<Integer, Vehicle> entriesIgnoredList = new HashMap<>();
        Map<Integer, Vehicle> entriesAssignedList = new HashMap<>();
        Map<Integer, Vehicle> entriesModifiedList = new HashMap<>();
        for(Vehicle vehicle: vehicleList){
            LOGGER.debug(vehicle.getVehicleId() + " : "
                    + (vehicle.getVehicleId()==null?"ID needs to be assigned":(vehicleRepository.existsById(vehicle.getVehicleId()))?
                    "ID already exists" : "unique ID found"));
            if(vehicle.getVehicleId()!=null && !vehicleRepository.existsById(vehicle.getVehicleId())
                    && vehicle.getModelNo()!=null && vehicle.getModelName() != null
                    && vehicle.getVehicleType()!=null
                    && vehicle.getManufacturerId()!=null && vehicle.getDom()!=null && vehicle.getLpc()!=null){
                LOGGER.debug("Saving vehicles data");
                vehicleRepository.save(vehicle);
                entriesCreatedList.put(entriesCreatedList.size(),vehicle);
            }
            else if(vehicle.getVehicleId()==null
                    && vehicle.getModelNo()!=null && vehicle.getModelName() != null
                    && vehicle.getVehicleType()!=null
                    && vehicle.getManufacturerId()!=null && vehicle.getDom()!=null && vehicle.getLpc()!=null){
                LOGGER.debug("Generating vehicles ID data");
                vehicle.setVehicleId(IdGenerator.generateUniqueId());
                LOGGER.debug("Saving vehicles data");
                vehicleRepository.save(vehicle);
                entriesAssignedList.put(entriesAssignedList.size(),vehicle);
            }
            else if(vehicle.getVehicleId()!=null && vehicleRepository.existsById(vehicle.getVehicleId())
                    && !skipUpdate
                    && vehicle.getModelNo()!=null && vehicle.getModelName() != null
                    && vehicle.getVehicleType()!=null
                    && vehicle.getManufacturerId()!=null && vehicle.getDom()!=null && vehicle.getLpc()!=null){
                LOGGER.debug("Modifying existing vehicle data");
                vehicleRepository.save(vehicle);
                entriesModifiedList.put(entriesModifiedList.size(),vehicle);
            }
            else{
                LOGGER.debug("Vehicle ignored - " + vehicle);
                entriesIgnoredList.put(entriesIgnoredList.size(),vehicle);
            }
        }

        Map<String, Map<Integer, Vehicle>> baseStats = new HashMap<>();
        baseStats.put(entriesCreated, entriesCreatedList);
        baseStats.put(entriesAssigned, entriesAssignedList);
        baseStats.put(entriesIgnored, entriesIgnoredList);
        baseStats.put(entriesModified, entriesModifiedList);

        Map<String, Integer> topStats = new HashMap<>();
        topStats.put(totalEntries, entriesAssignedList.size() + entriesCreatedList.size()
                    + entriesIgnoredList.size() + entriesModifiedList.size());
        topStats.put(entriesAssigned, entriesAssignedList.size());
        topStats.put(entriesCreated, entriesCreatedList.size());
        topStats.put(entriesIgnored, entriesIgnoredList.size());
        topStats.put(entriesModified, entriesModifiedList.size());

        VehiclesUpdateList vehiclesUpdateList = new VehiclesUpdateList(topStats, baseStats);
        ResponseMapper mapper = new ResponseMapper(HttpStatus.CREATED, "Vehicles Inserted Successfully",
                vehiclesUpdateList);
        return mapper;
    }

    public ResponseMapper importSingleVehicle(Vehicle vehicle){
        if(vehicle.getVehicleId()!=null && vehicleRepository.existsById(vehicle.getVehicleId()))
            return new ResponseMapper(HttpStatus.CONFLICT,"Vehicle ID already exists");
        else if(vehicle.getModelNo() == null)
            return new ResponseMapper(HttpStatus.BAD_REQUEST,"model no. cannot be null");
        else if(vehicle.getModelName() == null)
            return new ResponseMapper(HttpStatus.BAD_REQUEST,"model name cannot be null");
        else if(vehicle.getVehicleType() == null)
            return new ResponseMapper(HttpStatus.BAD_REQUEST,"vehicle type cannot be null or 0");
        else if(vehicle.getManufacturerId() == null)
            return new ResponseMapper(HttpStatus.BAD_REQUEST,"manufacturer ID cannot be null or 0");
        if(vehicle.getDom()==null)
            vehicle.setDom(new Date());
        if(vehicle.getLpc()==null)
            vehicle.setLpc(new Date());
        if(vehicle.getVehicleId()==null)
            vehicle.setVehicleId(IdGenerator.generateUniqueId());
        LOGGER.debug("Saving vehicles data");
        vehicleRepository.save(vehicle);

        return new ResponseMapper(HttpStatus.CREATED,"Vehicle Entry successful",
                vehicle);
    }

    public ResponseMapper getVehicleByID(int ID){
        if(this.vehicleRepository.existsById(ID))
            return new ResponseMapper(HttpStatus.FOUND, "Vehicle details found with given ID",
                    this.vehicleRepository.findByVehicleId(ID));
        return new ResponseMapper(HttpStatus.NOT_FOUND, "No vehicle Info found with given ID");
    }

    public ResponseMapper deleteVehicleID(int id) throws JsonProcessingException {
        if(vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return new ResponseMapper(HttpStatus.NO_CONTENT,"message", "Vehicle entry deleted successfully");
        }
        else{
            return new ResponseMapper(HttpStatus.NOT_FOUND,"message", "No vehicle with Given ID found");
        }
    }
}
