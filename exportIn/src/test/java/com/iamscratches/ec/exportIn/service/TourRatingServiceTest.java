package com.iamscratches.ec.exportIn.service;

import com.iamscratches.ec.exportIn.domain.Tour;
import com.iamscratches.ec.exportIn.domain.TourRating;
import com.iamscratches.ec.exportIn.repository.TourRatingRepository;
import com.iamscratches.ec.exportIn.repository.TourRespository;
import org.hibernate.HibernateException;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TourRatingServiceTest {
    private static final int CUSTOMER_ID = 123;
    private static final int TOUR_ID = 1;

    @Mock
    private TourRespository tourRespository;

    @Mock
    private TourRatingRepository tourRatingRepository;

    @InjectMocks
    private TourRatingService tourRatingService;

    @Mock
    private Tour tour;

    @Mock
    private TourRating tourRating;

    @Mock
    private Pageable pageable;

    @Mock
    private Page<TourRating> tourRatingPage;

    @Before
    public void setupReturnValuesOfServiceMethods(){
        when(tourRespository.findById(TOUR_ID)).thenReturn(Optional.of(tour));
        when(tourRatingRepository.findByTourId(TOUR_ID)).thenReturn(List.of(tourRating));
        when(tourRatingRepository.findByTourIdAndCustomerId(TOUR_ID,CUSTOMER_ID)).thenReturn(Optional.of(tourRating));
        when(tourRatingRepository.save(tourRating)).thenReturn(tourRating);
        when(tourRatingRepository.findByTourId(TOUR_ID, pageable)).thenReturn(tourRatingPage);
    }
    /**************************************************************************************
     *
     * Verify the invocation of dependencies
     * Capture parameter values.
     * Verify the parameters.
     *
     **************************************************************************************/

    @Test
    public void saveTourRatingTest(){
        //when save is successful
        assertEquals(tourRatingService.saveTourRating(tourRating), tourRating);
        //when save is unsuccessful
        when(tourRatingRepository.save(tourRating)).thenThrow(new HibernateException("Server not responding"));
        assertNull(tourRatingService.saveTourRating(tourRating));

    }

    @Test
    public void deleteTourRatingTest(){
        tourRatingService.deleteTourRating(tourRating);
        //verify tourRatingRepository.delete invoked
        verify(tourRatingRepository).delete(any(TourRating.class));
    }

    @Test
    public void findByTourIdTest(){
        assertEquals(tourRatingService.findByTourId(TOUR_ID, pageable),tourRatingPage);
    }

    @Test
    public void findByTourIdTest2(){
        assertEquals(tourRatingService.findByTourId(TOUR_ID),Arrays.asList(tourRating));
    }

    @Test
    public void findByIdTest(){
        Optional<Tour> optionalTour = Optional.of(tour);
        assertTrue(tourRatingService.findById(TOUR_ID).isPresent());
        assertEquals(tourRatingService.findById(TOUR_ID), optionalTour);
    }

    @Test
    public void findByTourIdAndCustomerIdTest(){
        Optional<TourRating> optionalTourRating = Optional.of(tourRating);
        assertTrue(tourRatingService.findByTourIdAndCustomerId(TOUR_ID, CUSTOMER_ID).isPresent());
        assertEquals(tourRatingService.findByTourIdAndCustomerId(TOUR_ID, CUSTOMER_ID), optionalTourRating);
    }

    @Test
    public void rateManyTest() {
        tourRatingService.rateMany(TOUR_ID, 2, new Integer[]{CUSTOMER_ID, CUSTOMER_ID + 1});
        verify(tourRespository).findById(anyInt());
        verify(tourRatingRepository, times(2)).save(any(TourRating.class));
    }
}
