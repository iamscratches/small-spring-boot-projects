package com.frontend.TMSFrontend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.frontend.TMSFrontend.util.ClientErrorHandler;
import com.frontend.TMSFrontend.util.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ViewDataService {

    Logger LOGGER = LoggerFactory.getLogger(ViewDataService.class);

    @Value("${iamscratches.TMS.service.url}")
    private String tmsUrl;

    @Autowired
    public OAuth2RestTemplate template;

    public ResponseMapper getAllVehicles() throws JsonProcessingException {
        try {
            return template.getForObject(tmsUrl + "/api/vehicle/vehiclelist", ResponseMapper.class);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }

    public ResponseMapper getAllUsersList() throws JsonProcessingException {
        try{
            return template.getForObject(tmsUrl + "/api/user/userslist", ResponseMapper.class);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }

    public ResponseMapper getAllTypeOfVehiclesList() throws JsonProcessingException {
        try{
            return template.getForObject(tmsUrl + "/api/tov/typeofvehicleslist", ResponseMapper.class);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }

    public ResponseMapper getAllRolesList() throws JsonProcessingException {
        try{
            return template.getForObject(tmsUrl + "/api/roles/roleslist", ResponseMapper.class);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }

    public ResponseMapper getAllRegistrationList() throws JsonProcessingException {
        try{
            return template.getForObject(tmsUrl + "/api/registration/registrationlist", ResponseMapper.class);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }

    public ResponseMapper getAllOwners() throws JsonProcessingException {
        try{
            return template.getForObject(tmsUrl + "/api/owner/ownerlist", ResponseMapper.class);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }

    public ResponseMapper getAllOffenceDetails() throws JsonProcessingException {
        try{
            return template.getForObject(tmsUrl + "/api/offencedetails/offencedetailslist", ResponseMapper.class);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }

    public ResponseMapper getAllOffence() throws JsonProcessingException {
        try{
            return template.getForObject(tmsUrl + "/api/offence/offencelist", ResponseMapper.class);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }

    public ResponseMapper getAllManufacturer() throws JsonProcessingException {
        try{
            return template.getForObject(tmsUrl + "/api/manufacturer/manufacturerlist", ResponseMapper.class);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }
}
