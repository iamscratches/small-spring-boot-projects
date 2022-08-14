package com.iamscratches.ec.exportIn.service;

import com.iamscratches.ec.exportIn.domain.Tour;
import com.iamscratches.ec.exportIn.domain.TourRating;
import com.iamscratches.ec.exportIn.repository.TourRatingRepository;
import com.iamscratches.ec.exportIn.repository.TourRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TourRatingService {
    public static final Logger LOGGER = LoggerFactory.getLogger(TourRatingService.class);

    TourRatingRepository tourRatingRepository;
    TourRespository tourRespository;

    @Autowired
    public TourRatingService(TourRatingRepository tourRatingRepository, TourRespository tourRespository) {
        this.tourRatingRepository = tourRatingRepository;
        this.tourRespository = tourRespository;
    }

    public TourRating saveTourRating(TourRating tourRating){
        try {
            return tourRatingRepository.save(tourRating);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public void deleteTourRating(TourRating rating){
        tourRatingRepository.delete(rating);
    }

    public Page<TourRating> findByTourId(int tourId, Pageable pageable){
        return tourRatingRepository.findByTourId(tourId, pageable);
    }

    public List<TourRating> findByTourId(int tourId){
        return tourRatingRepository.findByTourId(tourId);
    }

    public Optional<Tour> findById(int tourId){
        return tourRespository.findById(tourId);
    }

    public Optional<TourRating> findByTourIdAndCustomerId(int tourId,int customerId){
        return tourRatingRepository.findByTourIdAndCustomerId(tourId, customerId);
    }

    public void rateMany(int tourId, int score, Integer [] customers){
        LOGGER.info("Rate tour {} by customers {}", tourId, Arrays.asList(customers).toString());
        tourRespository.findById(tourId).ifPresent(tour -> {
            for(Integer c: customers){
                try {
                    LOGGER.debug("Attempt to create Tour rating for customer {}", c);
                    tourRatingRepository.save(new TourRating(tour, c, score));
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });
    }
}
