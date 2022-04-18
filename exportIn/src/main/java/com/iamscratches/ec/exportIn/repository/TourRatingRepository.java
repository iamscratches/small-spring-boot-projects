package com.iamscratches.ec.exportIn.repository;

import com.iamscratches.ec.exportIn.domain.TourRating;
import com.iamscratches.ec.exportIn.domain.TourRatingPk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TourRatingRepository extends CrudRepository<TourRating, TourRatingPk> {
    List<TourRating> findByPkTourId(Integer tourId);
    Optional<TourRating> findByPkTourIdAndPkCustomerId(Integer tourId, Integer customerId);
}
