package com.iamscratches.ec.exportIn.service;

import com.iamscratches.ec.exportIn.domain.Difficulty;
import com.iamscratches.ec.exportIn.domain.Region;
import com.iamscratches.ec.exportIn.domain.Tour;
import com.iamscratches.ec.exportIn.domain.TourPackage;
import com.iamscratches.ec.exportIn.repository.TourPackageRepository;
import com.iamscratches.ec.exportIn.repository.TourRespository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TourServiceTest {

    private static Difficulty difficulty = Difficulty.Easy;
    private static Region region = Region.Central_Coast;
    private String tourPackageName =  "tourPackageName";
    private long count = 5;

    @Mock
    private TourPackage tourPackage;

    @Mock
    private TourRespository tourRespository;

    @Mock
    private TourPackageRepository tourPackageRepository;

    @InjectMocks
    private TourService tourService;

    @Before
    public void setupReturnValuesOfServiceMethods(){
        when(tourPackageRepository.findByName(tourPackageName)).thenReturn(Optional.of(tourPackage));
        when(tourRespository.count()).thenReturn(count);
    }

    @Test
    public void createTourTest(){
        assertNull(tourService.createTour("title", "description", "blurb",
                202, "duration", "bullets", "keywords",
                "tourPackageName", difficulty, region));
        when(tourPackageRepository.findByName(tourPackageName)).thenReturn(Optional.empty());
        String msg = "Tour Package doesn't exist tourPackageName";
        try{
            tourService.createTour("title", "description", "blurb",
                    202, "duration", "bullets", "keywords",
                    "tourPackageName", difficulty, region);
        }catch (Exception e){
            assertEquals(e.getMessage(), msg);
        }
    }

    @Test
    public void totalTest(){
        assertEquals(tourService.total(), count);
    }
}
