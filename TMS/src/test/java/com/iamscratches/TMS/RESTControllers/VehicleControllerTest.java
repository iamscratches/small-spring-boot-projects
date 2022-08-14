//package com.iamscratches.TMS.RESTControllers;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.iamscratches.TMS.TmsApplication;
//import com.iamscratches.TMS.model.Vehicle;
//import com.iamscratches.TMS.service.VehicleService;
//import com.iamscratches.TMS.utils.JsonMapper;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(value = VehicleController.class)
//public class VehicleControllerTest {
//    public static final Logger LOGGER = LoggerFactory.getLogger(VehicleControllerTest.class);
//
//    private static final String vehicleListURL = "/api/vehicle/vehiclelist";
//
//    @Autowired
//    protected MockMvc mockMvc;
//
//    @MockBean
//    public VehicleService vehicleService;
//
//    @Before
//    public void setUp(){
//    }
///*
//    @Test
//    public void getAllVehiclesTest() throws Exception {
//        Date date = new Date();
//        Vehicle vehicle = new Vehicle("xxx1", "xxx2", 123,
//                "xxx3", 234, 345, date, 456,567,789,
//                901, date, false);
//
//        List<Vehicle> vehicles = Arrays.asList(vehicle, vehicle, vehicle);
//
//        when(vehicleService.getAllVehicles()).thenReturn(vehicles);
//
//        String vehiclesInJson = "";
//        try{
//            vehiclesInJson = JsonMapper.mapToJson(vehicles);
//            LOGGER.debug(vehiclesInJson);
//        }catch (Exception e){
//            LOGGER.error("Object to JSON convertion failed", e);
//        }
//
//
//        LOGGER.debug(vehiclesInJson);
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .get(vehicleListURL)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(vehiclesInJson)
//                .contentType(MediaType.APPLICATION_JSON);
//
//        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = mvcResult.getResponse();
//
//        String outputInJson = response.getContentAsString();
//
//        assertEquals(HttpStatus.OK.value(), response.getStatus());
//        assertEquals(outputInJson, vehiclesInJson);
//
//    }*/
//
//}
