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

    /*
    Create a new tour package
     */
    public TourPackage createTourPackage(String code, String name){

        return tourPackageRepository.findById(code).
                orElse(tourPackageRepository.save( new TourPackage(code, name)));
    }

    /*
    return all available tour packages
     */
    public Iterable<TourPackage> lookUp(){ return tourPackageRepository.findAll(); }

    /*
    return total no. of tour packages
     */
    public long total(){ return tourPackageRepository.count(); }
}
