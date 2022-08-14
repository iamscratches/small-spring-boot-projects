package com.iamscratches.ec.exportIn.web;

import com.iamscratches.ec.exportIn.domain.Tour;
import com.iamscratches.ec.exportIn.domain.TourRating;
import com.iamscratches.ec.exportIn.service.TourRatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/tours/{tourId}/ratings")
@Validated
public class TourRatingController {
    public static final Logger LOGGER = LoggerFactory.getLogger(TourRatingController.class);

    TourRatingService tourRatingService;

    public TourRatingController(TourRatingService tourRatingService) {
        this.tourRatingService = tourRatingService;
    }

    /*
    Creating multiple same tour rating for particular tour for different customers
     */
    @PostMapping("/{score}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createManyTourRatings(@PathVariable(value="tourId") int tourId,
                                      @PathVariable(value="score") int score,
                                      @RequestParam("customers") Integer customers[]){
        LOGGER.info("POST /tours/{}/ratings/{}", tourId, score);
        tourRatingService.rateMany(tourId, score, customers);
    }

    /*
    Create a new tour rating for a particular tourId
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTourRating(@Valid @RequestBody RatingDto ratingDto,
                                 @PathVariable(value = "tourId") int tourId){
        Tour tour = verifyTour(tourId);
        System.out.println(ratingDto);
        tourRatingService.saveTourRating(new TourRating(tour, ratingDto.getCustomerId(),
                ratingDto.getScore(), ratingDto.getComment()));
    }

    /*
    Get all tour ratings for a particular tourId
     */
    @GetMapping
    public Page<RatingDto> getAllRatingsForTour(@PathVariable(value = "tourId")int tourId,
                                                Pageable pageable){
        verifyTour(tourId);
        Page<TourRating> ratings = tourRatingService.findByTourId(tourId, pageable);
        return new PageImpl<>(ratings.get().map(RatingDto::new).collect(Collectors.toList()),
                pageable,
                ratings.getTotalElements());
    }

    /*
    Get average of all tour ratings for a particular tourId
     */
    @GetMapping(path = "/average")
    public Map<String, Double> getAverage(@PathVariable(value = "tourId")int tourId){
        verifyTour(tourId);
        return Map.of("average", tourRatingService.findByTourId(tourId).stream()
                .mapToInt(TourRating::getScore).average().orElseThrow(() ->
                        new NoSuchElementException("Tour has no ratings")));
    }

    /*
    Modify tour rating for a particular tourId and CustomerId mapping
     */
    @PutMapping
    public RatingDto updateWithPut(@Valid @RequestBody RatingDto ratingDto,
                                   @PathVariable(value = "tourId") int tourId){
        TourRating rating = verifyTourRating(tourId, ratingDto.getCustomerId());
        rating.setScore(ratingDto.getScore());
        rating.setComment(ratingDto.getComment());
        return new RatingDto(tourRatingService.saveTourRating(rating));
    }

    /*
    Modify comments or score for a particular tour rating
     */
    @PatchMapping
    public RatingDto updateWithPatch(@Valid @RequestBody RatingDto ratingDto,
                                     @PathVariable(value = "tourId") int tourId){
        TourRating rating = verifyTourRating(tourId, ratingDto.getCustomerId());
        if(ratingDto.getScore() != null){
            rating.setScore(ratingDto.getScore());
        }
        if(ratingDto.getComment() != null){
            rating.setComment(ratingDto.getComment());
        }
        return new RatingDto(tourRatingService.saveTourRating(rating));
    }

    /*
    Delete a particular tourRating for a particular tourId and customerId
     */
    @DeleteMapping(path = "/{customerId}")
    public void delete(@PathVariable(value = "tourId")int tourId,
                       @PathVariable(value = "customerId")int customerId){
        TourRating rating = verifyTourRating(tourId, customerId);
        tourRatingService.deleteTourRating(rating);
    }

    private Tour verifyTour(int tourId) throws NoSuchElementException{
        return tourRatingService.findById(tourId).orElseThrow(() -> new NoSuchElementException(
                "No such tour exists named " + tourId
        ));
    }

    private TourRating verifyTourRating(int tourId, int customerId) throws NoSuchElementException{
        return tourRatingService.findByTourIdAndCustomerId(tourId,customerId).orElseThrow(() ->
                new NoSuchElementException("Tour rating pair for request(" + tourId + " for customer "
                + customerId));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex){
        LOGGER.error("Unable to complete transaction", ex);
        return ex.getMessage();
    }
}
