package com.iamscratches.ec.exportIn.web;

import com.iamscratches.ec.exportIn.domain.Tour;
import com.iamscratches.ec.exportIn.repository.TourPackageRepository;
import com.iamscratches.ec.exportIn.repository.TourRespository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TourControllerTest {
    private static final String TOUR_URL = "/api/tours";
    private static final String TOUR_PACKAGE_URL = "/api/tourspackage";

    @MockBean
    private Tour tour;

    @MockBean
    private TourPackageRepository tourPackageRepository;

    @MockBean
    private TourRespository tourRespository;

    @InjectMocks
    private TourController tourController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setupReturnValuesOfMockMethods() {
        Iterable<Tour> tourIterable = new Iterable<Tour>() {
            @Override
            public Iterator<Tour> iterator() {
                return Arrays.asList(tour, tour, tour).iterator();
            }
        };
        when(tourRespository.findAll()).thenReturn(Arrays.asList(tour, tour, tour));
    }

    @Test
    public void getAllToursTest(){
        ResponseEntity<List<Tour>> response = restTemplate.exchange(TOUR_URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Tour>>() {});
        assertEquals(response.getStatusCode(), HttpStatus.OK);
//        assertEquals(response.getBody().size(), 3);
        System.out.print(response.getBody().size());
    }
}
