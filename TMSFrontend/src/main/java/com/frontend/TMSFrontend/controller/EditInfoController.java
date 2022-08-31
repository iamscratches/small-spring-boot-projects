package com.frontend.TMSFrontend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.frontend.TMSFrontend.model.*;
import com.frontend.TMSFrontend.service.EditInfoService;
import com.frontend.TMSFrontend.util.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class EditInfoController {
    Logger LOGGER = LoggerFactory.getLogger(EditInfoController.class);

    @Autowired
    private final EditInfoService service;

    public EditInfoController(EditInfoService service) {
        LOGGER.debug("EditDataController Autowired with EditInfoService");
        this.service = service;
    }

    @GetMapping(value="/vehicle/add")
    @PreAuthorize("hasAuthority('RTO')")
    public String getAddVehicleForm(Model model){
        return "view/vehicle-view";
    }

    @GetMapping(value="/user/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAdduserForm(Model model){
        return "view/user-view";
    }

    @GetMapping(value="/tov/add")
    @PreAuthorize("hasAuthority('CLERK')")
    public String getAddtovForm(Model model){
        return "view/typeOfVehicle-view";
    }

    @GetMapping(value="/role/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAddroleForm(Model model){
        return "view/role-view";
    }

    @GetMapping(value="/registration/add")
    @PreAuthorize("hasAuthority('RTO')")
    public String getAddregistrationForm(Model model){
        return "view/registration-view";
    }

    @GetMapping(value="/owner/add")
    @PreAuthorize("hasAuthority('RTO')")
    public String getAddownerForm(Model model){
        return "view/owner-view";
    }

    @GetMapping(value="/offencedetail/add")
    @PreAuthorize("hasAuthority('TRAFFIC_POLICE')")
    public String getAddoffencedetailForm(Model model){
        return "view/offencedetail-view";
    }

    @GetMapping(value="/offence/add")
    @PreAuthorize("hasAuthority('CLERK')")
    public String getAddoffenceForm(Model model){
        return "view/offence-view";
    }

    @GetMapping(value="/manufacturer/add")
    @PreAuthorize("hasAuthority('CLERK')")
    public String getAddmanufacturerForm(Model model){
        return "view/manufacturer-view";
    }

    @PostMapping(value="/vehicle/add")
    @PreAuthorize("hasAuthority('RTO')")
    public String updateVehicle(Model model, @ModelAttribute Vehicle vehicle) throws JsonProcessingException {
        LOGGER.debug("Vehicle : " + vehicle);
        ResponseMapper mapper = service.editVehicleInfo(vehicle);
        LOGGER.debug(mapper.getResponse().getMessage());
        model.addAttribute("message", mapper.getResponse().getMessage());
        model.addAttribute("vehicle", mapper.getValue());
        return "view/vehicle-view";
    }

    @PostMapping(value="/user/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateUser(Model model, @ModelAttribute User user) throws JsonProcessingException {
        LOGGER.debug("User : " + user);
        ResponseMapper mapper = service.editUserInfo(user);
        LOGGER.debug(mapper.getResponse().getMessage());
        model.addAttribute("message", mapper.getResponse().getMessage());
        model.addAttribute("user", mapper.getValue());
        return "view/user-view";
    }

    @PostMapping(value="/typeOfVehicle/add")
    @PreAuthorize("hasAuthority('CLERK')")
    public String updateTypeOfVehicles(Model model, @ModelAttribute TypeOfVehicles typeOfVehicle) throws JsonProcessingException {
        LOGGER.debug("TypeOfVehicles : " + typeOfVehicle);
        ResponseMapper mapper = service.editTypeOfVehiclesInfo(typeOfVehicle);
        LOGGER.debug(mapper.getResponse().getMessage());
        model.addAttribute("message", mapper.getResponse().getMessage());
        model.addAttribute("tov", mapper.getValue());
        return "view/typeOfVehicle-view";
    }

    @PostMapping(value="/role/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateRoles(Model model, @ModelAttribute Roles role) throws JsonProcessingException {
        LOGGER.debug("Roles : " + role);
        ResponseMapper mapper = service.editRolesInfo(role);
        LOGGER.debug(mapper.getResponse().getMessage());
        model.addAttribute("message", mapper.getResponse().getMessage());
        model.addAttribute("role", mapper.getValue());
        return "view/role-view";
    }

    @PostMapping(value="/registration/add")
    @PreAuthorize("hasAuthority('RTO')")
    public String updateRegistration(Model model, @ModelAttribute Registration registration) throws JsonProcessingException {
        LOGGER.debug("Registration : " + registration);
        ResponseMapper mapper = service.editRegistrationInfo(registration);
        LOGGER.debug(mapper.getResponse().getMessage());
        model.addAttribute("message", mapper.getResponse().getMessage());
        model.addAttribute("registration", mapper.getValue());
        return "view/registration-view";
    }

    @PostMapping(value="/owner/add")
    @PreAuthorize("hasAuthority('RTO')")
    public String updateOwner(Model model, @ModelAttribute Owner owner) throws JsonProcessingException {
        LOGGER.debug("Owner : " + owner);
        ResponseMapper mapper = service.editOwnerInfo(owner);
        LOGGER.debug(mapper.getResponse().getMessage());
        model.addAttribute("message", mapper.getResponse().getMessage());
        model.addAttribute("owner", mapper.getValue());
        return "view/owner-view";
    }

    @PostMapping(value="/offencedetail/add")
    @PreAuthorize("hasAuthority('TRAFFIC_POLICE')")
    public String updateOffenceDetails(Model model, @ModelAttribute OffenceDetails offencedetail) throws JsonProcessingException {
        LOGGER.debug("OffenceDetails : " + offencedetail);
        ResponseMapper mapper = service.editOffenceDetailsInfo(offencedetail);
        LOGGER.debug(mapper.getResponse().getMessage());
        model.addAttribute("message", mapper.getResponse().getMessage());
        model.addAttribute("offencedetail", mapper.getValue());
        return "view/offencedetail-view";
    }

    @PostMapping(value="/offence/add")
    @PreAuthorize("hasAuthority('CLERK')")
    public String updateOffence(Model model, @ModelAttribute Offence offence) throws JsonProcessingException {
        LOGGER.debug("Offence : " + offence);
        ResponseMapper mapper = service.editOffenceInfo(offence);
        LOGGER.debug(mapper.getResponse().getMessage());
        model.addAttribute("message", mapper.getResponse().getMessage());
        model.addAttribute("offence", mapper.getValue());
        return "view/offence-view";
    }

    @PostMapping(value="/manufacturer/add")
    @PreAuthorize("hasAuthority('CLERK')")
    public String updateManufacturer(Model model, @ModelAttribute Manufacturer manufacturer) throws JsonProcessingException {
        LOGGER.debug("Manufacturer : " + manufacturer);
        ResponseMapper mapper = service.editManufacturerInfo(manufacturer);
        LOGGER.debug(mapper.getResponse().getMessage());
        model.addAttribute("message", mapper.getResponse().getMessage());
        model.addAttribute("manufacturer", mapper.getValue());
        return "view/manufacturer-view";
    }
}
