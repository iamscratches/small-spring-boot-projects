package com.example.iamscratches.guestapp;


import com.example.iamscratches.guestapp.domain.Guest;
import com.example.iamscratches.guestapp.domain.GuestModel;
import com.example.iamscratches.guestapp.service.GuestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Frank P. Moley III.
 */
@Controller
@RequestMapping("/")
public class GuestController {

    Logger LOGGER = LoggerFactory.getLogger(GuestController.class);

    private final GuestService guestService;

    public GuestController(GuestService guestService){
        super();
        this.guestService = guestService;
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

    @GetMapping(value="/guests")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getGuests(Model model){
        List<Guest> guests = this.guestService.getAllGuests();
        model.addAttribute("guests", guests);
        return "guests-view";
    }

    @GetMapping(value="/guests/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddGuestForm(Model model){
        return "guest-view";
    }

    @PostMapping(value="/guests")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addGuest(HttpServletRequest request, Model model, @ModelAttribute GuestModel guestModel){
        Guest guest = this.guestService.addGuest(guestModel);
        model.addAttribute("guest", guest);
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
        return new ModelAndView("redirect:/guests/" + guest.getId());
    }

    @GetMapping(value="/guests/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getGuest(Model model, @PathVariable long id){
        Guest guest = this.guestService.getGuest(id);
        model.addAttribute("guest", guest);
        return "guest-view";
    }

    @PostMapping(value="/guests/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateGuest(Model model, @PathVariable long id, @ModelAttribute GuestModel guestModel) {
        Guest guest = this.guestService.updateGuest(id, guestModel);
        model.addAttribute("guest", guest);
        model.addAttribute("guestModel", new GuestModel());
        return "guest-view";
    }

    @GetMapping(value="/somerandom")
    @PreAuthorize("hasRole('ROLE_SOMERANDOM')")
    public String getsomerandom(Model model){
        List<Guest> guests = this.guestService.getAllGuests();
        model.addAttribute("guests", guests);
        return "guests-view";
    }

    @GetMapping(value="/maintainer")
    @PreAuthorize("hasRole('ROLE_MAINTAINER')")
    public String getmAINTAINER(Model model){
        List<Guest> guests = this.guestService.getAllGuests();
        model.addAttribute("guests", guests);
        return "guests-view";
    }

    @GetMapping(value="/entrylevel")
    @PreAuthorize("hasRole('ROLE_ENTRYLEVEL')")
    public String getentrylevel(Model model){
        List<Guest> guests = this.guestService.getAllGuests();
        model.addAttribute("guests", guests);
        return "guests-view";
    }
}
