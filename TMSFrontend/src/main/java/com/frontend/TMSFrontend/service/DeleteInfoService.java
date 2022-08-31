package com.frontend.TMSFrontend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.frontend.TMSFrontend.util.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class DeleteInfoService {
    Logger LOGGER = LoggerFactory.getLogger(DeleteInfoService.class);

    @Value("${iamscratches.TMS.service.url}")
    private String tmsUrl;

    @Autowired
    public RestTemplate template;

    public void deleteVehicleInfo(int ID){
        template.delete(tmsUrl + "/api/vehicle?ID="+ID);
    }

    public void deleteUserInfo(String username){
        template.delete(tmsUrl + "/api/user?username="+username);
    }

    public void deleteVehicleTypeInfo(int ID){
        template.delete(tmsUrl + "/api/tov?identifierType=VEHICLE_TYPE_ID&identifierValue="+ID);
    }

    public void deleteRoleInfo(String username,String rolename){
        template.delete(tmsUrl + "/api/roles?username="+username + "&rolename=" + rolename);
    }

    public void deleteRegistrationInfo(int ID){
        template.delete(tmsUrl + "/api/registration?appNo="+ID);
    }

    public void deleteOwnerInfo(int ID){
        template.delete(tmsUrl + "/api/owner?identifierType=OWNER_ID&identifierValue="+ID);
    }

    public void deleteOffenceDetails(int ID){
        template.delete(tmsUrl + "/api/offencedetails?appNo="+ID);
    }

    public void deleteOffenceInfo(int ID){
        template.delete(tmsUrl + "/api/offence?offenceID="+ID);
    }

    public void deleteManufacturerInfo(int ID){
        template.delete(tmsUrl + "/api/manufacturer?ID="+ID);
    }
}
