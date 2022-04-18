package com.iamscratches.ec.exportIn.service;

import com.iamscratches.ec.exportIn.domain.Difficulty;
import com.iamscratches.ec.exportIn.domain.Region;
import com.iamscratches.ec.exportIn.domain.Tour;
import com.iamscratches.ec.exportIn.domain.TourPackage;
import com.iamscratches.ec.exportIn.repository.TourPackageRepository;
import com.iamscratches.ec.exportIn.repository.TourRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourService {
    private TourRespository tourRespository;
    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourService(TourRespository tourRespository, TourPackageRepository tourPackageRepository) {
        this.tourRespository = tourRespository;
        this.tourPackageRepository = tourPackageRepository;
    }

    public Tour createTour(String title, String description, String blurb, Integer price, String duration,
                           String bullets, String keywords, String tourPackageName, Difficulty difficulty,
                           Region region){
        TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName).orElseThrow(() ->
                new RuntimeException("Tour Package doesn't exist " + tourPackageName));
        return tourRespository.save(new Tour(title, description, blurb, price, duration, bullets, keywords, tourPackage,
                difficulty, region));
    }

    public long total(){
        return tourRespository.count();
    }



}
