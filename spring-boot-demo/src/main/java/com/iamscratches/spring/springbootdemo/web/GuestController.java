package com.iamscratches.spring.springbootdemo.web;

import com.iamscratches.spring.springbootdemo.business.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/guests")
public class GuestController {
    private final ReservationService reservationService;

    public GuestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getGuest(Model model){
        model.addAttribute("guests", this.reservationService.getHotelGuests());
        return "hotel-guests";
    }
}
