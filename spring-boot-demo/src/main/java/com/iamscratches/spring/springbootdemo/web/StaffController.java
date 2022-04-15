package com.iamscratches.spring.springbootdemo.web;

import com.iamscratches.spring.springbootdemo.business.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/staffs")
public class StaffController {
    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public String getAllStaffs(Model model){
        model.addAttribute("allStaffs",staffService.getAllStaffs());
        return "staffs";
    }
}
