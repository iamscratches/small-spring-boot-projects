package com.iamscratches.ec.exportIn.service;

import com.iamscratches.ec.exportIn.domain.TourPackage;
import com.iamscratches.ec.exportIn.repository.TourPackageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TourPackageServiceTest {

    private static final String code = "CB";
    private static final String name = "California Beaches";
    private static final long count = 5;

    @Mock
    private TourPackageRepository tourPackageRepository;

    @Mock
    private TourPackage tourPackage;

    @InjectMocks
    private TourPackageService tourPackageService;

    @Before
    public void setupReturnValuesOfServiceMethods(){
        when(tourPackageRepository.findById(code)).thenReturn(Optional.of(tourPackage));
        when(tourPackageRepository.findAll()).thenReturn(List.of(tourPackage, tourPackage, tourPackage));
        when(tourPackageRepository.count()).thenReturn(count);
    }

    @Test
    public void createTourPackageTest(){
        assertEquals(tourPackageService.createTourPackage(code, name), tourPackage);
        when(tourPackageRepository.findById(code)).thenReturn(Optional.empty());
        assertNull(tourPackageService.createTourPackage(code, name));
    }

    @Test
    public void lookUpTest(){
        assertEquals(tourPackageService.lookUp(), Arrays.asList(tourPackage,tourPackage, tourPackage));
    }

    @Test
    public void totalTest(){
        assertEquals(tourPackageService.total(), count);
    }


}
