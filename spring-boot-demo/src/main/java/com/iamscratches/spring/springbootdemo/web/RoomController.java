package com.iamscratches.spring.springbootdemo.web;

import com.iamscratches.spring.springbootdemo.business.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final ReservationService reservationService;

    public RoomController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getRooms(Model model){
        model.addAttribute("room", reservationService.getRooms());
        return "rooms";
    }
}
