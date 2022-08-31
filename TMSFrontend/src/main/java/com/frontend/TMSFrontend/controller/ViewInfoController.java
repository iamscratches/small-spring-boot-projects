package com.frontend.TMSFrontend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frontend.TMSFrontend.service.ViewInfoService;
import com.frontend.TMSFrontend.util.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ViewInfoController {
    Logger LOGGER = LoggerFactory.getLogger(ViewInfoController.class);

    @Autowired
    private final ViewInfoService service;

    public ViewInfoController(ViewInfoService service) {
        LOGGER.debug("ViewInfoController Autowired with ViewInfoService");
        this.service = service;
    }

    @GetMapping(value = "/vehicle/{ID}")
    @PreAuthorize("hasAuthority('RTO')")
    public String getVehicle(Model model,@PathVariable int ID)throws JsonProcessingException {
        ResponseMapper mapper = service.getVehicleInfo(ID);
        if(mapper.getResponse().getResponseCode()!= HttpStatus.FOUND){
            model.addAttribute("error", mapper.getResponse());
            return "backend-error";
        }
        LOGGER.debug(mapper.getResponse().getMessage());
        model.addAttribute("vehicle", mapper.getValue());
        return "view/vehicle-view";
    }

    @GetMapping(value = "/user/{username}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getUser(Model model, @PathVariable String username) throws JsonProcessingException {
        ResponseMapper mapper = service.getUserInfo(username);
        if(mapper.getResponse().getResponseCode()!= HttpStatus.FOUND){
            model.addAttribute("error", mapper.getResponse());
            return "backend-error";
        }
        LOGGER.debug(mapper.getResponse().getMessage());
        model.addAttribute("user", mapper.getValue());
        return "view/user-view";
    }

    @GetMapping(value = "/typeOfVehicle/{ID}")
    @PreAuthorize("hasAuthority('CLERK')")
    public String getTypeOfVehicle(Model model, @PathVariable int ID) throws JsonProcessingException {
        ResponseMapper mapper = service.getTypeOfVehicleInfo(ID);
        if(mapper.getResponse().getResponseCode()!= HttpStatus.FOUND){
            model.addAttribute("error", mapper.getResponse());
            return "backend-error";
        }
        LOGGER.debug(mapper.getResponse().getMessage());
        model.addAttribute("tov", mapper.getValue());
        return "view/typeOfVehicle-view";
    }

    @GetMapping(value = "/role/{username}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getRole(Model model, @PathVariable String username) throws JsonProcessingException {
        ResponseMapper mapper = service.getRoleInfo(username);
        if(mapper.getResponse().getResponseCode()!= HttpStatus.FOUND){
            model.addAttribute("error", mapper.getResponse());
            return "backend-error";
        }
        LOGGER.debug(mapper.getResponse().getMessage());
        model.addAttribute("role", mapper.getValue());
        return "view/role-view";
    }

    @GetMapping(value = "/registration/{ID}")
    @PreAuthorize("hasAuthority('RTO')")
    public String getUsersList(Model model, @PathVariable int ID) throws JsonProcessingException {
        ResponseMapper mapper = service.getRegistrationInfo(ID);
        if(mapper.getResponse().getResponseCode()!= HttpStatus.FOUND){
            model.addAttribute("error", mapper.getResponse());
            return "backend-error";
        }
        LOGGER.debug(mapper.getResponse().getMessage());
        LOGGER.debug(mapper.getResponse().getMessage());
        model.addAttribute("registration", mapper.getValue());
        return "view/registration-view";
    }

    @GetMapping(value = "/owner/{ID}")
    @PreAuthorize("hasAuthority('RTO')")
    public String getOwner(Model model, @PathVariable int ID) throws JsonProcessingException {
        ResponseMapper mapper = service.getOwnerInfo(ID);
        if(mapper.getResponse().getResponseCode()!= HttpStatus.FOUND){
            model.addAttribute("error", mapper.getResponse());
            return "backend-error";
        }
        LOGGER.debug(mapper.getResponse().getMessage());
        model.addAttribute("owner", mapper.getValue());
        return "view/owner-view";
    }

    @GetMapping(value = "/offenceDetail/{ID}")
    @PreAuthorize("hasAuthority('RTO') or hasAuthority('TRAFFIC_POLICE')")
    public String getOffenceDetail(Model model, @PathVariable int ID) throws JsonProcessingException {
        ResponseMapper mapper = service.getOffenceDetailsInfo(ID);
        if(mapper.getResponse().getResponseCode()!= HttpStatus.FOUND){
            model.addAttribute("error", mapper.getResponse());
            return "backend-error";
        }
        LOGGER.debug(mapper.getResponse().getMessage());
        model.addAttribute("offencedetail", mapper.getValue());
        return "view/offenceDetail-view";
    }

    @GetMapping(value = "/offence/{ID}")
    @PreAuthorize("hasAuthority('CLERK')")
    public String getOffence(Model model, @PathVariable int ID) throws JsonProcessingException {
        ResponseMapper mapper = service.getOffenceInfo(ID);
        if(mapper.getResponse().getResponseCode()!= HttpStatus.FOUND){
            model.addAttribute("error", mapper.getResponse());
            return "backend-error";
        }
        LOGGER.debug(mapper.getResponse().getMessage());
        model.addAttribute("offence", mapper.getValue());
        return "view/offence-view";
    }

    @GetMapping(value = "/manufacturer/{ID}")
    @PreAuthorize("hasAuthority('CLERK')")
    public String getManufacturer(Model model, @PathVariable int ID) throws JsonProcessingException {
        ResponseMapper mapper = service.getManufacturerInfo(ID);
        if(mapper.getResponse().getResponseCode()!= HttpStatus.FOUND){
            model.addAttribute("error", mapper.getResponse());
            return "backend-error";
        }
        LOGGER.debug(mapper.getResponse().getMessage());
        model.addAttribute("manufacturer", mapper.getValue());
        return "view/manufacturer-view";
    }
}
