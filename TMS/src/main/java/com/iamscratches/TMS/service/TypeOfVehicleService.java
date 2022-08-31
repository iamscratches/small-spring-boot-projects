package com.iamscratches.TMS.service;

import com.iamscratches.TMS.model.TypeOfVehicles;
import com.iamscratches.TMS.repo.TypeOfVehicleRepository;
import com.iamscratches.TMS.utils.IdGenerator;
import com.iamscratches.TMS.utils.model.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TypeOfVehicleService {
    public static final Logger LOGGER = LoggerFactory.getLogger(TypeOfVehicleService.class);

    private final TypeOfVehicleRepository repository;

    @Autowired
    public TypeOfVehicleService(TypeOfVehicleRepository repository) {
        LOGGER.debug("TypeOfVehicleService Autowired with TypeOfVehicleRepository");
        this.repository = repository;
    }

    public ResponseMapper getAllTypesOfVehicles(){
        try{
            LOGGER.debug("Recieved TypeOfVehicles List request");
            Iterable<TypeOfVehicles> typeOfVehicles = this.repository.findAll();
            List<TypeOfVehicles> typeOfVehiclesList = new ArrayList<>();
            typeOfVehicles.forEach(typeOfVehiclesList::add);
            LOGGER.debug("Send TypeOfVehicles List");
            return new ResponseMapper(HttpStatus.OK, "TOV details fetched successfully",
                    typeOfVehiclesList);
        }catch (Exception e){
            LOGGER.error("ToV fetch failed : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to fetch Type of Vehicle details");
        }
    }

    public ResponseMapper insertTOV(TypeOfVehicles tov){
        LOGGER.debug("Recieved Vehicle Type insert request");
        try{
            LOGGER.debug("Recieved TypeOfVehicles save request");
            if(tov.getVehicleCategory()==null) {
                return new ResponseMapper(HttpStatus.BAD_REQUEST, "Vehicle Category cannot be empty");
            }
            if(repository.existsByVehicleCategory(tov.getVehicleCategory())){
                return new ResponseMapper(HttpStatus.CONFLICT, "Vehicle Category already exists",
                        repository.findByVehicleCategory(tov.getVehicleCategory()));
            }
            if(tov.getVehicleTypeId() != null && repository.existsById(tov.getVehicleTypeId())){
                return new ResponseMapper(HttpStatus.NOT_ACCEPTABLE, "Vehicle is already assigned given ID",
                        repository.findById(tov.getVehicleTypeId()));
            }

            if(tov.getVehicleTypeId()==null){
                tov.setVehicleTypeId(IdGenerator.generateUniqueId());
            }
            repository.save(tov);
            return new ResponseMapper(HttpStatus.CREATED,
                    "Type of Vehicle entry created successfully", tov);
        }catch (Exception e){
            LOGGER.error("ToV entry failed to save : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT, "Something went wrong, not able to save Type of Vehicle details");
        }

    }

    public ResponseMapper deleteByVehicleID(Integer vehicleId){
        LOGGER.debug("Recieved Vehicle Type delete request by vehicleID");
        try{
            if(!repository.existsById(vehicleId)){
                return new ResponseMapper(HttpStatus.NOT_FOUND,
                        "No vehicle type with given ID is present to be deleted");
            }
            repository.deleteById(vehicleId);
            return new ResponseMapper(HttpStatus.OK, "Vehicle type entry deleted successfully");
        }catch (Exception e){
            LOGGER.error("ToV entry failed to delete : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to delete Type of Vehicle details");
        }
    }

    public ResponseMapper deleteByVehicleCategory(String vehicleCategory){
        LOGGER.debug("Recieved Vehicle Type delete request by vehicle category");
        try {
            if (!repository.existsByVehicleCategory(vehicleCategory)) {
                return new ResponseMapper(HttpStatus.NOT_FOUND, "No vehicle type with given category found");
            }
            repository.deleteByVehicleCategory(vehicleCategory);
            return new ResponseMapper(HttpStatus.NO_CONTENT, "Vehicle type entry deleted successfully");
        }catch (Exception e){
            LOGGER.error("ToV entry failed to delete : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to save Type of Vehicle details");
        }
    }

    public ResponseMapper getTypeOfVehicleById(int ID){
        if(this.repository.existsById(ID))
            return new ResponseMapper(HttpStatus.FOUND, "TOV info found with given ID",
                    this.repository.findById(ID));
        return new ResponseMapper(HttpStatus.NOT_FOUND, "No TOV Info found with given ID");
    }


}
