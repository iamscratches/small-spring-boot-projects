package com.frontend.TMSFrontend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontend.TMSFrontend.model.User;
import com.frontend.TMSFrontend.util.ClientErrorHandler;
import com.frontend.TMSFrontend.util.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViewInfoService {
    Logger LOGGER = LoggerFactory.getLogger(ViewInfoService.class);

    @Value("${iamscratches.TMS.service.url}")
    private String tmsUrl;

    @Autowired
    public RestTemplate template;

    public ResponseMapper getVehicleInfo(int ID) throws JsonProcessingException {
        try {
            return template.getForObject(tmsUrl + "/api/vehicle?ID="+ID, ResponseMapper.class);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }

    public ResponseMapper getUserInfo(String username) throws JsonProcessingException {
        ResponseMapper mapper;
        try{
            mapper =  template.getForObject(tmsUrl + "/api/user?ID="+username, ResponseMapper.class);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
        LOGGER.debug("Received username " + mapper.getResponse().getResponseCode());
        return mapper;
    }

    public ResponseMapper getTypeOfVehicleInfo(int ID) throws JsonProcessingException {
        try{
            return template.getForObject(tmsUrl + "/api/tov?ID="+ID, ResponseMapper.class);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }

    public ResponseMapper getRoleInfo(String username) throws JsonProcessingException {
        try{
            return template.getForObject(tmsUrl + "/api/roles?ID="+username, ResponseMapper.class);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }

    public ResponseMapper getRegistrationInfo(int ID) throws JsonProcessingException {
        try{
            return template.getForObject(tmsUrl + "/api/registration?ID="+ID, ResponseMapper.class);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }

    public ResponseMapper getOwnerInfo(int ID) throws JsonProcessingException {
        try{
            return template.getForObject(tmsUrl + "/api/owner?ID="+ID, ResponseMapper.class);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }

    public ResponseMapper getOffenceDetailsInfo(int ID) throws JsonProcessingException {
        try{
            return template.getForObject(tmsUrl + "/api/offencedetails?ID="+ID, ResponseMapper.class);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }

    public ResponseMapper getOffenceInfo(int ID) throws JsonProcessingException {
        try{
            return template.getForObject(tmsUrl + "/api/offence?ID="+ID, ResponseMapper.class);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }

    public ResponseMapper getManufacturerInfo(int ID) throws JsonProcessingException {
        try{
            return template.getForObject(tmsUrl + "/api/manufacturer?ID="+ID, ResponseMapper.class);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ClientErrorHandler.errorConverter(e.getLocalizedMessage());
        }
    }
}
