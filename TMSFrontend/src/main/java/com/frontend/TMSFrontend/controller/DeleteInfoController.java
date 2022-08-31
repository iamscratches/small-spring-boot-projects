package com.frontend.TMSFrontend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.frontend.TMSFrontend.service.DeleteInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class DeleteInfoController {
    Logger LOGGER = LoggerFactory.getLogger(DeleteInfoController.class);

    @Autowired
    private final DeleteInfoService service;

    public DeleteInfoController(DeleteInfoService service) {
        LOGGER.debug("DeleteInfoController Autowired with DeleteInfoService");
        this.service = service;
    }

    @GetMapping(value = "/vehicle/delete/{ID}")
    @PreAuthorize("hasAuthority('RTO')")
    public ModelAndView deleteVehicle(Model model, @PathVariable int ID){
        service.deleteVehicleInfo(ID);
        LOGGER.debug("deleted Vehicle with Vehicle ID " + ID );
        return new ModelAndView("redirect:/vehiclelist");
    }

    @GetMapping(value = "/user/delete/{username}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView deleteUserInfo(Model model, @PathVariable String username){
        service.deleteUserInfo(username);
        LOGGER.debug("deleted User with username " + username );
        return new ModelAndView("redirect:/userlist");
    }

    @GetMapping(value = "/typeOfVehicle/delete/{ID}")
    @PreAuthorize("hasAuthority('CLERK')")
    public ModelAndView deleteTOV(Model model, @PathVariable int ID){
        service.deleteVehicleTypeInfo(ID);
        LOGGER.debug("deleted typeOfVehicle with VehicleType ID " + ID );
        return new ModelAndView("redirect:/typeofvehiclelist");
    }

    @GetMapping(value = "/role/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView deleteRole(Model model,
                                   @RequestParam(value="username") String username,
                                   @RequestParam(value="rolename") String rolename){
        service.deleteRoleInfo(username, rolename);
        LOGGER.debug("deleted Role with username " + username + ", " + "and rolename " + rolename );
        return new ModelAndView("redirect:/rolelist");
    }

    @GetMapping(value = "/registration/delete/{ID}")
    @PreAuthorize("hasAuthority('RTO')")
    public ModelAndView deleteResgistration(Model model, @PathVariable int ID) throws JsonProcessingException {
        service.deleteRegistrationInfo(ID);
        LOGGER.debug("deleted Registration with Registration ID " + ID );
        return new ModelAndView("redirect:/registrationlist");
    }

    @GetMapping(value = "/owner/delete/{ID}")
    @PreAuthorize("hasAuthority('RTO')")
    public ModelAndView deleteOwner(Model model, @PathVariable int ID){
        service.deleteOwnerInfo(ID);
        LOGGER.debug("deleted Owner with Owner ID " + ID );
        return new ModelAndView("redirect:/ownerlist");
    }

    @GetMapping(value = "/offenceDetail/delete/{ID}")
    @PreAuthorize("hasAuthority('RTO') or hasAuthority('TRAFFIC_POLICE')")
    public ModelAndView deleteOffenceDetails(Model model, @PathVariable int ID){
        service.deleteOffenceDetails(ID);
        LOGGER.debug("deleted Offence Details with ID " + ID );
        return new ModelAndView("redirect:/offencedetailsList");
    }

    @GetMapping(value = "/offence/delete/{ID}")
    @PreAuthorize("hasAuthority('CLERK')")
    public ModelAndView deleteOffence(Model model, @PathVariable int ID){
        service.deleteOffenceInfo(ID);
        LOGGER.debug("deleted Offence with ID " + ID );
        return new ModelAndView("redirect:/offencelist");
    }

    @GetMapping(value = "/manufacturer/delete/{ID}")
    @PreAuthorize("hasAuthority('CLERK')")
    public ModelAndView deleteManufacturer(Model model, @PathVariable int ID){
        service.deleteManufacturerInfo(ID);
        LOGGER.debug("deleted Manufacturer with ID " + ID );
        return new ModelAndView("redirect:/manufacturerlist");
    }
}
