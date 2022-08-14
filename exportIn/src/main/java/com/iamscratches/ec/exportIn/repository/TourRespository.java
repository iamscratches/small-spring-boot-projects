package com.iamscratches.ec.exportIn.repository;

import com.iamscratches.ec.exportIn.domain.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TourRespository extends PagingAndSortingRepository<Tour, Integer> {
    public Page<Tour> findByTourPackageCode(String code, Pageable pageable);
    public List<Tour> findAll();
}
