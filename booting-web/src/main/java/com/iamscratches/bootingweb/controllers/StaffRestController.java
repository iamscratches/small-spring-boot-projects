package com.iamscratches.bootingweb.controllers;

import com.iamscratches.bootingweb.models.Staff;
import com.iamscratches.bootingweb.service.StaffService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/staffs")
public class StaffRestController {
    private final StaffService staffService;

    public StaffRestController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public List<Staff> getAllStaffs(){
        return staffService.getAllStaffs();
    }
}
