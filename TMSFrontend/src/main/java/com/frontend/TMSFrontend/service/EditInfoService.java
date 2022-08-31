package com.frontend.TMSFrontend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.frontend.TMSFrontend.model.*;
import com.frontend.TMSFrontend.util.ClientErrorHandler;
import com.frontend.TMSFrontend.util.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EditInfoService {
    Logger LOGGER = LoggerFactory.getLogger(EditInfoService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${iamscratches.TMS.service.url}")
    private String tmsUrl;

    @Autowired
    public RestTemplate template;

    public ResponseMapper editVehicleInfo(Vehicle vehicle) throws JsonProcessingException {
        LOGGER.debug("Received edit request for vehicle info");
        try {
            HttpEntity<Vehicle> request = new HttpEntity<>(vehicle, null);
            return template.exchange(tmsUrl + "/api/vehicle/import", HttpMethod.PUT, request, ResponseMapper.class).getBody();
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }

    public ResponseMapper editUserInfo(User user) throws JsonProcessingException {

        LOGGER.debug("Received edit request for vehicle info");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        LOGGER.debug("Encoded password successfully with " + user.getPassword());
        try {
            HttpEntity<User> request = new HttpEntity<>(user, null);
            return template.exchange(tmsUrl + "/api/user/import", HttpMethod.PUT, request, ResponseMapper.class).getBody();
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }

    public ResponseMapper editTypeOfVehiclesInfo(TypeOfVehicles tov) throws JsonProcessingException {
        LOGGER.debug("Received edit request for tov info");
        try {
            HttpEntity<TypeOfVehicles> request = new HttpEntity<>(tov, null);
            return template.exchange(tmsUrl + "/api/tov/import", HttpMethod.PUT, request, ResponseMapper.class).getBody();
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }

    public ResponseMapper editRolesInfo(Roles roles) throws JsonProcessingException {
        LOGGER.debug("Received edit request for roles info");
        try {
            HttpEntity<Roles> request = new HttpEntity<>(roles, null);
            return template.exchange(tmsUrl + "/api/roles/import", HttpMethod.PUT, request, ResponseMapper.class).getBody();
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }

    public ResponseMapper editRegistrationInfo(Registration registration) throws JsonProcessingException {
        LOGGER.debug("Received edit request for registration info");
        try {
            HttpEntity<Registration> request = new HttpEntity<>(registration, null);
            return template.exchange(tmsUrl + "/api/registration/import", HttpMethod.PUT, request, ResponseMapper.class).getBody();
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }

    public ResponseMapper editOwnerInfo(Owner owner) throws JsonProcessingException {
        LOGGER.debug("Received edit request for owner info");
        try {
            HttpEntity<Owner> request = new HttpEntity<>(owner, null);
            return template.exchange(tmsUrl + "/api/owner/import", HttpMethod.PUT, request, ResponseMapper.class).getBody();
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }
    public ResponseMapper editOffenceDetailsInfo(OffenceDetails offencedetails) throws JsonProcessingException {
        LOGGER.debug("Received edit request for offencedetails info");
        try {
            HttpEntity<OffenceDetails> request = new HttpEntity<>(offencedetails, null);
            return template.exchange(tmsUrl + "/api/offencedetails/import", HttpMethod.PUT, request, ResponseMapper.class).getBody();
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }

    public ResponseMapper editOffenceInfo(Offence offence) throws JsonProcessingException {
        LOGGER.debug("Received edit request for offence info");
        try {
            HttpEntity<Offence> request = new HttpEntity<>(offence, null);
            return template.exchange(tmsUrl + "/api/offence/import", HttpMethod.PUT, request, ResponseMapper.class).getBody();
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }

    public ResponseMapper editManufacturerInfo(Manufacturer manufacturer) throws JsonProcessingException {
        LOGGER.debug("Received edit request for manufacturer info");
        try {
            HttpEntity<Manufacturer> request = new HttpEntity<>(manufacturer, null);
            return template.exchange(tmsUrl + "/api/manufacturer/import", HttpMethod.PUT, request, ResponseMapper.class).getBody();
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }
}
