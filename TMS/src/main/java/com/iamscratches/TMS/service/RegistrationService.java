package com.iamscratches.TMS.service;

import com.iamscratches.TMS.model.Registration;
import com.iamscratches.TMS.model.User;
import com.iamscratches.TMS.repo.RegistrationRepository;
import com.iamscratches.TMS.repo.UserRepository;
import com.iamscratches.TMS.utils.IdGenerator;
import com.iamscratches.TMS.utils.model.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RegistrationService {
    public static final Logger LOGGER = LoggerFactory.getLogger(RegistrationService.class);
    private final String ALL_OK = "ALL_OK";

    private final RegistrationRepository repository;

    @Autowired
    public RegistrationService(RegistrationRepository repository) {
        LOGGER.debug("RegistrationService Autowired with RegistrationRepository");
        this.repository = repository;
    }

    public ResponseMapper getAllRegistrations(){
        try{
            LOGGER.debug("Recieved Registration List request");
            Iterable<Registration> registrations = this.repository.findAll();
            List<Registration> registrationList = new ArrayList<>();
            registrations.forEach(registrationList::add);

            LOGGER.debug("Send Registration List");
            return new ResponseMapper(HttpStatus.OK, "Fetched All Registration", registrationList);
        }catch (Exception e){
            LOGGER.error("Registration fetch failed : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to fetch Registration details");
        }
    }

    public ResponseMapper insertRegistration(Registration registration){
        String msg = validateRegistration(registration);
        if(msg!=ALL_OK)
            return new ResponseMapper(HttpStatus.NOT_ACCEPTABLE, msg);
        try{
            LOGGER.debug("Received insert request for new and verified Registration");
            repository.save(registration);
            LOGGER.debug("Registration entry saved successfully");
            return new ResponseMapper(HttpStatus.CREATED,
                    "Registration details saved successfully", registration);
        }catch (Exception e){
            LOGGER.error("Registration insertion failed : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to insert Registration details");
        }
    }

    public ResponseMapper deleteByAppNo(Integer appNo){
        LOGGER.debug("Recieved Registration delete request");
        try{
            if(!repository.existsById(appNo)){
                return new ResponseMapper(HttpStatus.NOT_FOUND,
                        "No Registration with given ID is present to be deleted");
            }
            repository.deleteById(appNo);
            return new ResponseMapper(HttpStatus.OK, "Registration entry deleted successfully");
        }catch (Exception e){
            LOGGER.error("Registration entry failed to delete : " + e.getMessage());
            return new ResponseMapper(HttpStatus.GATEWAY_TIMEOUT,
                    "Something went wrong, not able to delete Registration details");
        }
    }

    public ResponseMapper getRegistrationById(int ID){
        if(this.repository.existsById(ID))
            return new ResponseMapper(HttpStatus.FOUND, "Registration info found with given ID",
                    this.repository.findById(ID));
        return new ResponseMapper(HttpStatus.NOT_FOUND, "No Registration Info found with given ID");
    }

    private String validateRegistration(Registration registration){
        if(registration.getAppNo()==null)
            registration.setAppNo(IdGenerator.generateUniqueId());
        if(registration.getVehicleNo()==null)
            registration.setVehicleNo(IdGenerator.generateUniqueId());
        if(registration.getDateOfRetention()==null)
            registration.setDateOfRetention(new Date());
        Date date = registration.getDateOfRetention();
        if(registration.getDateOfExpiry()==null)
            registration.setDateOfExpiry(new Date(date.getYear()+15, date.getMonth(), date.getDay()));

        if(registration.getVehicleId()==null)
            return "Please provide registered vehicle ID";
        else if(repository.existsById(registration.getAppNo()))
            return "Application ID already exists";
        else if(repository.existsByVehicleNo(registration.getVehicleNo()))
            return "Please provide a unique Vehicle No";
        else if(repository.existsByVehicleIdAndOwnerId(registration.getVehicleId(), registration.getOwnerId()))
            return "Vehicle ID already registered with other owner";
        else if(repository.existsByVehicleId(registration.getVehicleId()))
            return "Vehicle ID already registered";

        registration.setValid(registration.getDateOfExpiry().after(registration.getDateOfRetention()));

        return ALL_OK;
    }


}
