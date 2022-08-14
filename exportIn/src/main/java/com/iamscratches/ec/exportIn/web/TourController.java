package com.iamscratches.ec.exportIn.web;

import com.iamscratches.ec.exportIn.domain.Tour;
import com.iamscratches.ec.exportIn.domain.TourPackage;
import com.iamscratches.ec.exportIn.repository.TourPackageRepository;
import com.iamscratches.ec.exportIn.repository.TourRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TourController {

    Logger logger = LoggerFactory.getLogger(TourController.class);

    TourPackageRepository tourPackageRepository;
    TourRespository tourRespository;

    public TourController(TourPackageRepository tourPackageRepository, TourRespository tourRespository) {
        this.tourPackageRepository = tourPackageRepository;
        this.tourRespository = tourRespository;
    }

    /*
    Get all tours
     */
    @RequestMapping("/tours")
    @GetMapping
    public List<Tour> getAllTours(){
        logger.trace("getAllTours() is called");
        List<Tour> tours = tourRespository.findAll();
        for(Tour tour: tours)
            logger.debug(tour.toString());
        return tours;
    }

    /*
    Get all tour packages available
     */
    @RequestMapping("/tourspackage")
    @GetMapping
    public List<TourPackage> getAllTourPackage(){
        return (List<TourPackage>) tourPackageRepository.findAll();
    }
}
