package com.iamscratches.ec.exportIn.web;

import com.iamscratches.ec.exportIn.domain.Tour;
import com.iamscratches.ec.exportIn.domain.TourPackage;
import com.iamscratches.ec.exportIn.repository.TourPackageRepository;
import com.iamscratches.ec.exportIn.repository.TourRatingRepository;
import com.iamscratches.ec.exportIn.repository.TourRespository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TourController {
    TourPackageRepository tourPackageRepository;
    TourRespository tourRespository;

    public TourController(TourPackageRepository tourPackageRepository, TourRespository tourRespository) {
        this.tourPackageRepository = tourPackageRepository;
        this.tourRespository = tourRespository;
    }

    @RequestMapping("/tours")
    @GetMapping
    public List<Tour> getAllTours(){
        List<Tour> tours = (List<Tour>) tourRespository.findAll();
        return tours;
    }

    @RequestMapping("/tourspackage")
    @GetMapping
    public List<TourPackage> getAllTourPackage(){
        return (List<TourPackage>) tourPackageRepository.findAll();
    }
}
