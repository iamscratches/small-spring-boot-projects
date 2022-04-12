package com.iamscratches.bootingweb.service;

import com.iamscratches.bootingweb.models.Position;
import com.iamscratches.bootingweb.models.Staff;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StaffService {

    private static final List<Staff> staff = new ArrayList();

    static {
        staff.add(new Staff(UUID.randomUUID().toString(), "John", "Doe", Position.CONCEIRGE.toString()));
        staff.add(new Staff(UUID.randomUUID().toString(), "Jane", "Doe", Position.FRONT_DESK.toString()));
        staff.add(new Staff(UUID.randomUUID().toString(), "Will", "Smith", Position.HOUSEKEEPING.toString()));
        staff.add(new Staff(UUID.randomUUID().toString(), "Chris", "Rock", Position.SECURITY.toString()));
    }

    public static List<Staff> getAllStaffs() {
        return staff;
    }
}
