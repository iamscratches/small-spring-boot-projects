package com.iamscratches.spring.springbootdemo.business;

import com.iamscratches.spring.springbootdemo.data.Staff;
import com.iamscratches.spring.springbootdemo.data.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class StaffService {
    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> getAllStaffs(){
        return staffRepository.findAll();
//        Iterable<Staff> staffs = staffRepository.findAll();
//        List<Staff> allStaffs = new ArrayList();
//        staffs.forEach(staff -> {
//            allStaffs.add(staff);
//        });
//        allStaffs.sort(new Comparator<Staff>() {
//            @Override
//            public int compare(Staff o1, Staff o2) {
//                if(o1.getFirstName().equals(o2.getFirstName())){
//                    return o1.getLastName().compareTo(o2.getLastName());
//                }
//                return o1.getFirstName().compareTo(o2.getFirstName());
//            }
//        });
//
//        return allStaffs;
    }
}
