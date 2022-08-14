package com.iamscratches.ec.exportIn.repository;

import com.iamscratches.ec.exportIn.domain.TourPackage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface TourPackageRepository extends CrudRepository<TourPackage, String> {
    Optional<TourPackage> findByName(String name);
    public List<TourPackage> findAll();
}
