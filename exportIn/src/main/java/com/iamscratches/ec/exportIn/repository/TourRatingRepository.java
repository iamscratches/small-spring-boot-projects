package com.iamscratches.ec.exportIn.repository;

import com.iamscratches.ec.exportIn.domain.TourRating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TourRatingRepository extends CrudRepository<TourRating, Integer> {
    List<TourRating> findByTourId(Integer tourId);
    Optional<TourRating> findByTourIdAndCustomerId(Integer tourId, Integer customerId);
    Page<TourRating> findByTourId(Integer tourId, Pageable pageable);
}
