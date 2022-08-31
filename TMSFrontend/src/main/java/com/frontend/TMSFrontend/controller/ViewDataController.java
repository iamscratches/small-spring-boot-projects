package com.frontend.TMSFrontend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.frontend.TMSFrontend.service.ViewDataService;
import com.frontend.TMSFrontend.util.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;

@Controller
@RequestMapping("/")
public class ViewDataController {
    Logger LOGGER = LoggerFactory.getLogger(ViewDataController.class);

    @Autowired
    private final ViewDataService service;

    public ViewDataController(ViewDataService service) {
        LOGGER.debug("ViewDataController Autowired with ViewDataService");
        this.service = service;
    }

    @GetMapping(value={"/", "/index"})
    public String getHomePage(Model model){
        return "index";
    }

    @GetMapping(value = "/login")
    public String getLoginPage(Model model){
        return "login";
    }

    @GetMapping(value = "/logout-success")
    public String getLogoutPage(Model model){
        return "logout";
    }

    @PreAuthorize("hasAuthority('RTO')")
    @GetMapping(value = "/vehiclelist")
    public String getVehicleList(Model model)throws JsonProcessingException {
        ResponseMapper mapper = service.getAllVehicles();
        if(mapper.getResponse().getResponseCode()!= HttpStatus.OK){
            model.addAttribute("error", mapper.getResponse());
            return "backend-error";
        }
        LOGGER.debug(mapper.toString());
        model.addAttribute("vehicles", mapper.getValue());
        return "views/vehicles-view";
    }

    @GetMapping(value = "/userlist")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getUsersList(Model model) throws JsonProcessingException {
        ResponseMapper mapper = service.getAllUsersList();
        if(mapper.getResponse().getResponseCode()!= HttpStatus.OK){
            model.addAttribute("error", mapper.getResponse());
            return "backend-error";
        }
        LOGGER.debug(mapper.toString());
        model.addAttribute("users", mapper.getValue());
        return "views/users-view";
    }

    @GetMapping(value = "/typeofvehiclelist")
    @PreAuthorize("hasAuthority('CLERK')")
    public String getTypeOfVehiclesList(Model model) throws JsonProcessingException {
        ResponseMapper mapper = service.getAllTypeOfVehiclesList();
        if(mapper.getResponse().getResponseCode()!= HttpStatus.OK){
            model.addAttribute("error", mapper.getResponse());
            return "backend-error";
        }
        LOGGER.debug(mapper.toString());
        model.addAttribute("typeOfVehicles", mapper.getValue());
        return "views/typeOfVehicles-view";
    }

    @GetMapping(value = "/rolelist")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getRolesList(Model model) throws JsonProcessingException {
        ResponseMapper mapper = service.getAllRolesList();
        if(mapper.getResponse().getResponseCode()!= HttpStatus.OK){
            model.addAttribute("error", mapper.getResponse());
            return "backend-error";
        }
        LOGGER.debug(mapper.toString());
        model.addAttribute("roles", mapper.getValue());
        return "views/roles-view";
    }

    @GetMapping(value = "/registrationlist")
    @PreAuthorize("hasAuthority('RTO')")
    public String getRegistrationList(Model model) throws JsonProcessingException {
        ResponseMapper mapper = service.getAllRegistrationList();
        if(mapper.getResponse().getResponseCode()!= HttpStatus.OK){
            LOGGER.error("Error occured in backend while fetching data");
            model.addAttribute("error", mapper.getResponse());
            return "backend-error";
        }
        LOGGER.debug(mapper.toString());
        model.addAttribute("registrations", mapper.getValue());
        return "views/registrations-view";
    }

    @GetMapping(value = "/ownerlist")
    @PreAuthorize("hasAuthority('RTO')")
    public String getOwnerList(Model model) throws JsonProcessingException {
        ResponseMapper mapper = service.getAllOwners();
        if(mapper.getResponse().getResponseCode()!= HttpStatus.OK){
            model.addAttribute("error", mapper.getResponse());
            return "backend-error";
        }
        LOGGER.debug(mapper.toString());
        model.addAttribute("owners", mapper.getValue());
        return "views/owners-view";
    }

    @GetMapping(value = "/offencedetailsList")
    @PreAuthorize("hasAuthority('RTO') or hasAuthority('TRAFFIC_POLICE')")
    public String getOffenceDetailsList(Model model) throws JsonProcessingException {
        ResponseMapper mapper = service.getAllOffenceDetails();
        if(mapper.getResponse().getResponseCode()!= HttpStatus.OK){
            model.addAttribute("error", mapper.getResponse());
            return "backend-error";
        }
        LOGGER.debug(mapper.toString());
        model.addAttribute("offenceDetails", mapper.getValue());
        return "views/offenceDetails-view";
    }

    @GetMapping(value = "/offencelist")
    @PreAuthorize("hasAuthority('CLERK')")
    public String getOffenceList(Model model) throws JsonProcessingException {
        ResponseMapper mapper = service.getAllOffence();
        if(mapper.getResponse().getResponseCode()!= HttpStatus.OK){
            model.addAttribute("error", mapper.getResponse());
            return "backend-error";
        }
        LOGGER.debug(mapper.toString());
        model.addAttribute("offences", mapper.getValue());
        return "views/offences-view";
    }

    @GetMapping(value = "/manufacturerlist")
    @PreAuthorize("hasAuthority('CLERK')")
    public String getManufacturerList(Model model) throws JsonProcessingException {
        ResponseMapper mapper = service.getAllManufacturer();
        if(mapper.getResponse().getResponseCode()!= HttpStatus.OK){
            model.addAttribute("error", mapper.getResponse());
            return "backend-error";
        }
        LOGGER.debug(mapper.toString());
        model.addAttribute("manufacturers", mapper.getValue());
        return "views/manufacturers-view";
    }
}
