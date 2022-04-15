package com.iamscratches.spring.springbootdemo.webservice;

import com.iamscratches.spring.springbootdemo.business.StaffService;
import com.iamscratches.spring.springbootdemo.data.Staff;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StaffServiceController {
    private final StaffService staffService;

    public StaffServiceController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/staffs")
    public List<Staff> getAllStaffs(){
        return staffService.getAllStaffs();
    }
}
