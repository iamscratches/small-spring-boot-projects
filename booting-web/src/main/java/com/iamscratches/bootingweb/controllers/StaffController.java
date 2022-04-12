package com.iamscratches.bootingweb.controllers;

import com.iamscratches.bootingweb.models.Staff;
import com.iamscratches.bootingweb.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staff")
public class StaffController {
    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    private String getAllStaffs(Model model){
        model.addAttribute("staffDetails", staffService.getAllStaffs());
        return "staffs";
    }
}
