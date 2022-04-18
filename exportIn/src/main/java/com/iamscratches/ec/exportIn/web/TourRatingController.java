package com.iamscratches.ec.exportIn.web;

import com.iamscratches.ec.exportIn.domain.Tour;
import com.iamscratches.ec.exportIn.domain.TourRating;
import com.iamscratches.ec.exportIn.domain.TourRatingPk;
import com.iamscratches.ec.exportIn.repository.TourRatingRepository;
import com.iamscratches.ec.exportIn.repository.TourRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/tours/{tourId}/ratings")
@Validated
public class TourRatingController {
    TourRatingRepository tourRatingRepository;
    TourRespository tourRespository;

    @Autowired
    public TourRatingController(TourRatingRepository tourRatingRepository, TourRespository tourRespository) {
        this.tourRatingRepository = tourRatingRepository;
        this.tourRespository = tourRespository;
    }

    protected TourRatingController() {
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTourRating(@Valid @RequestBody RatingDto ratingDto,
                                 @PathVariable(value = "tourId") int tourId){
        Tour tour = verifyTour(tourId);
        System.out.println(ratingDto);
        tourRatingRepository.save(new TourRating(new TourRatingPk(tour, ratingDto.getCustomerId()),
                ratingDto.getScore(), ratingDto.getComment()));
    }

    @GetMapping
    public List<RatingDto> getAllRatingsForTour(@PathVariable(value = "tourId")int tourId){
        verifyTour(tourId);
        return tourRatingRepository.findByPkTourId(tourId).stream()
                .map(RatingDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/average")
    public Map<String, Double> getAverage(@PathVariable(value = "tourId")int tourId){
        verifyTour(tourId);
        return Map.of("average", tourRatingRepository.findByPkTourId(tourId).stream()
                .mapToInt(TourRating::getScore).average().orElseThrow(() ->
                        new NoSuchElementException("Tour has no ratings")));
    }

    private Tour verifyTour(int tourId) throws NoSuchElementException{
        return tourRespository.findById(tourId).orElseThrow(() -> new NoSuchElementException(
                "No such tour exists named " + tourId
        ));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex){
        return ex.getMessage();
    }
}
