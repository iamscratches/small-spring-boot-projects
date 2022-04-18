package com.iamscratches.ec.exportIn.service;

import com.iamscratches.ec.exportIn.domain.TourPackage;
import com.iamscratches.ec.exportIn.repository.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourPackageService {
    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourPackageService(TourPackageRepository tourPackageRepository) {
        this.tourPackageRepository = tourPackageRepository;
    }

    public TourPackage createTourPackage(String code, String name){

        return tourPackageRepository.findById(code).
                orElse(tourPackageRepository.save( new TourPackage(code, name)));
    }

    public Iterable<TourPackage> lookUp(){ return tourPackageRepository.findAll(); }

    public long total(){ return tourPackageRepository.count(); }
}
